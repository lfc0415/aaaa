package com.pullo.story.controller

import com.pullo.story.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * @author by liufucheng on 2020/4/15
 */
@Controller
class AuthController {
    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @PostMapping("/register")
    fun registerUser(@RequestBody registerUser: Map<String?, String?>): String? {
        val user = User()
        user.username = registerUser["username"]
        user.password = bCryptPasswordEncoder!!.encode(registerUser["password"])
        user.role = "ROLE_USER"
        return "SUCCESS"
    }
}
