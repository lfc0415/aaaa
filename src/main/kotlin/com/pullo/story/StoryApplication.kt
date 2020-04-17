package com.pullo.story

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@MapperScan("com.pullo.story.mybatis.mapper")
@SpringBootApplication
class StoryApplication

fun main(args: Array<String>) {
    runApplication<StoryApplication>(*args)
}
