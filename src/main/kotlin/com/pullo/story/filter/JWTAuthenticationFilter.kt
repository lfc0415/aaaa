package com.pullo.story.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.pullo.story.model.User
import com.pullo.story.model.auth.JwtUser
import com.pullo.story.util.JwtTokenUtils
import com.pullo.story.util.JwtTokenUtils.createToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.ArrayList
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {
    private val rememberMeThreadLocal = ThreadLocal<Int>()
    private val _authenticationManager: AuthenticationManager = authenticationManager

    init {
        super.setFilterProcessesUrl("/auth/login")
    }

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication? {
        // 从输入流中获取到登录的信息
        return try {
            val user = ObjectMapper().readValue(
                request.inputStream,
                User::class.java
            )
            rememberMeThreadLocal.set(user.rememberMe)
            _authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    user.account,
                    user.password,
                    ArrayList()
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 成功验证后调用的方法
     * 如果验证成功，就生成token并返回
     */
    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val jwtUser = authResult.principal as JwtUser
        val isRemember = rememberMeThreadLocal.get() == 1
        var role = ""
        val authorities = jwtUser.authorities
        for (authority in authorities) {
            role = authority!!.authority
        }
        val token = createToken(jwtUser.username, role, isRemember)
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token)
    }

    @Throws(IOException::class, ServletException::class)
    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        response.writer.write("authentication failed, reason: " + failed.message)
    }
}
