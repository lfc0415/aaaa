package com.pullo.story.model.dto

/**
 * @author by liufucheng on 2020/4/22
 */
class PoetryDTO {
    var uuid: String? = null
    var author: String? = null
    var authorId: String? = null
    var title: String? = null
    var paragraphs: List<String>? = null
    var text: String? = null
    var likeCount: Int = 0
    var readCount: Int = 0
    var collectCount: Int = 0
}
