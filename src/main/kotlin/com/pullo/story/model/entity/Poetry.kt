package com.pullo.story.model.entity

import org.joda.time.DateTime

/**
 * @author by liufucheng on 2020/4/23
 */
class Poetry {
    var id: Int? = null
    var uuid: String? = null
    var author: String? = null
    var authorId: String? = null
    var title: String? = null
    var text: String? = null
    val likeCount: Int = 0
    val readCount: Int = 0
    val collectCount: Int = 0
    var createTime: DateTime? = null
    var updateTime: DateTime? = null
}
