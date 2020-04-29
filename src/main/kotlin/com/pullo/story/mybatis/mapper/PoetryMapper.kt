package com.pullo.story.mybatis.mapper

import com.pullo.story.model.entity.Poetry

/**
 * @author by liufucheng on 2020/4/23
 */
interface PoetryMapper {
    fun insert(poetry: Poetry)
}
