package com.pullo.story.model

/**
 * @author by liufucheng on 2020/4/15
 */
data class User(
    var id: Int? = null,
    var username: String? = null,
    var password: String? = null,
    var role: String? = null,
    var rememberMe:Int = 0
)
