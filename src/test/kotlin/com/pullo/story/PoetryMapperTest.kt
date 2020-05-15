package com.pullo.story

import com.pullo.story.json.JsonConverter
import com.pullo.story.mybatis.mapper.PoetryMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author by liufucheng on 2020/5/15
 */
class PoetryMapperTest : StoryApplicationTests() {
    @Autowired
    lateinit var poetryMapper: PoetryMapper

    @Test
    fun `test find poems by author`() {
        val aa = poetryMapper.findByTitleOrAuthor("李白")
        print(JsonConverter.serialize(aa))
    }
}
