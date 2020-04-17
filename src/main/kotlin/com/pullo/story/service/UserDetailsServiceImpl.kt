package com.pullo.story.service

import com.pullo.story.model.User
import com.pullo.story.model.auth.JwtUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * Created by echisan on 2018/6/23
 */
@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(s: String): UserDetails {
        val user: User = User().apply {
            username = "name1"
            role = "admin"
        }
        return JwtUser(user)
    }
}
