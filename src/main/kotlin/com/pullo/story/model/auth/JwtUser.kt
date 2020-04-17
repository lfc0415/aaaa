package com.pullo.story.model.auth

import com.pullo.story.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by echisan on 2018/6/23
 */
class JwtUser : UserDetails {
    var id: Int? = null
    private var username: String? = null
    private var password: String? = null
    private var authorities: Collection<GrantedAuthority?>? = null

    constructor() {}
    /**
     * 写一个能直接使用user创建jwtUser的构造器
      */
    constructor(user: User) {
        id = user.id
        username = user.username
        password = user.password
        authorities = setOf(SimpleGrantedAuthority(user.role))
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return authorities!!
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return username!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
