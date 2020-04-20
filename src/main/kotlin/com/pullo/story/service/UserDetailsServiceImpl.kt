package com.pullo.story.service

import com.pullo.story.exception.PulloError
import com.pullo.story.model.auth.JwtUser
import com.pullo.story.mybatis.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl @Autowired constructor(
    private val userMapper: UserMapper
) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(account: String): UserDetails {
        val user = userMapper.getUserByAccount(account)
            ?: throw PulloError.NOT_FOUND.errorCode("user_not_found").toPulloRuntimeException()
        return JwtUser(user)
    }
}
