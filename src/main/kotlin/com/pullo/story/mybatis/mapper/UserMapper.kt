package com.pullo.story.mybatis.mapper

import com.pullo.story.model.User

/**
 * @author by liufucheng on 2020/4/20
 */
interface UserMapper {
    fun getUserByAccount(account: String): User?

    fun insert(user: User): Int

    fun update(user: User): Int
}
