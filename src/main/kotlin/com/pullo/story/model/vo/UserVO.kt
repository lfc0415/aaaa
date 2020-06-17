package com.pullo.story.model.vo

import org.joda.time.DateTime
import javax.validation.constraints.NotBlank

/**
 * @author by liufucheng on 2020/4/20
 */
class UserVO {
    var account: String = ""
    @NotBlank
    var username: String = ""
    @NotBlank
    var password: String = ""
    var mobilePhone: String? = null
    var emailAddress: String? = null
    var gender: String? = null
    var birthDate: DateTime? = null
    var address: String? = null
    var rememberMe: Boolean = false
}
