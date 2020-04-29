package com.pullo.story

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

/**
 * @author by liufucheng on 2020/4/22
 */
class TestLogger : StoryApplicationTests() {
    val logger = LoggerFactory.getLogger("aaa")
    @Test
    fun aa() {
        logger.info("aaa")
    }
}
