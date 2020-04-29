package com.pullo.story.batch

import com.pullo.story.StoryApplicationTests
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author by liufucheng on 2020/4/23
 */
class JsonImportBatchTest : StoryApplicationTests() {
    @Autowired
    lateinit var jsonImportBatch: JsonImportBatch

    @Test
    fun `test sync`() {
        jsonImportBatch.reader()
    }
}
