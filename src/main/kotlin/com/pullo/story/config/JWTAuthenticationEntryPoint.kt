package com.pullo.story.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationEntryPoint : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.characterEncoding = "UTF-8"
        response.contentType = "application/json; charset=utf-8"
        response.status = HttpServletResponse.SC_FORBIDDEN
        val reason = "auth failed：" + authException.message
        response.writer.write(ObjectMapper().writeValueAsString(reason))
    }
}
