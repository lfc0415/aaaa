package com.pullo.story.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.pullo.story.json.JsonConverter

/**
 * @author by liufucheng on 2020/4/22
 */
class PoetryDTO {
    @JsonProperty("id")
    var uuid: String? = null
    var author: String? = null
    var title: String? = null
    @JsonProperty("paragraphs")
    var paragraphs: List<String>? = null
    var text: String? = null
        get() = JsonConverter.serialize(paragraphs)
}
