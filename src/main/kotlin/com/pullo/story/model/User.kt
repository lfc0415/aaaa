package com.pullo.story.model

import org.joda.time.DateTime

/**
 * @author by liufucheng on 2020/4/15
 */
class User {
    var id: Int? = null
    var account: String = ""
    var username: String? = null
    var password: String = ""
    var mobilePhone: String? = null
    var emailAddress: String? = null
    var gender: String? = null
    var birthDate: DateTime? = null
    var address: String? = null
    var role: String = "COMMON"
    var lastLogin: DateTime? = null
    var createTime: DateTime? = null
    var updateTime: DateTime? = null
    var rememberMe: Int = 0
}
