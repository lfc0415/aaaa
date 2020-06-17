package com.pullo.story.controller

import com.pullo.story.exception.PulloError
import com.pullo.story.model.User
import com.pullo.story.model.auth.JwtUser
import com.pullo.story.model.vo.UserVO
import com.pullo.story.mybatis.mapper.UserMapper
import com.pullo.story.util.JwtTokenUtils
import org.springframework.beans.BeanUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

/**
 * @author by liufucheng on 2020/4/15
 */
@RestController
class AuthController(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val userMapper: UserMapper,
    private val myAuthenticationManager: AuthenticationManager
) {
    @PostMapping("/register")
    fun registerUser(@RequestBody userVO: UserVO): String {
        val user = User().apply {
            BeanUtils.copyProperties(userVO, this)
        }
        val dbUser = userMapper.getUserByAccount(userVO.account)
        if (dbUser != null) {
            throw PulloError.INVALID_ARGUMENT.errorCode("user.already_exist").toPulloRuntimeException()
        }
        user.password = bCryptPasswordEncoder.encode(userVO.password)
        user.role = "COMMON"
        userMapper.insert(user)
        return "SUCCESS"
    }

    @PostMapping("/logon")
    fun logon(response: HttpServletResponse, @RequestBody userVO: UserVO): String {
        val authResult = myAuthenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userVO.account,
                userVO.password,
                listOf()
            )
        )
        val jwtUser = authResult.principal as JwtUser
        val authorities = jwtUser.authorities
        val token = JwtTokenUtils.createToken(jwtUser.username, authorities.first()?.authority ?: "", userVO.rememberMe)
        response.setHeader(JwtTokenUtils.TOKEN_HEADER, JwtTokenUtils.TOKEN_PREFIX + token)
        return token
    }
}
