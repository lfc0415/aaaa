package com.pullo.story.controller

import com.pullo.story.exception.PulloError
import com.pullo.story.model.User
import com.pullo.story.model.vo.UserVO
import com.pullo.story.mybatis.mapper.UserMapper
import org.springframework.beans.BeanUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @author by liufucheng on 2020/4/15
 */
@RestController
class AuthController(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val userMapper: UserMapper
) {
    @PostMapping("/register")
    fun registerUser(@RequestBody userVO: UserVO): String? {
        val user = User().apply {
            BeanUtils.copyProperties(userVO, this)
        }
        userMapper.getUserByAccount(userVO.account)
            ?: throw PulloError.INVALID_ARGUMENT.errorCode("user.already_exist").toPulloRuntimeException()
        user.password = bCryptPasswordEncoder.encode(userVO.password)
        user.role = "COMMON"
        userMapper.insert(user)
        return "SUCCESS"
    }
}
