package com.pullo.story.controller.view

import com.pullo.story.mybatis.mapper.PoetryMapper
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

/**
 * @author by liufucheng on 2020/5/9
 */
@Controller
@RequestMapping("/poems")
class PoetryController(
    private val poetryMapper: PoetryMapper
) {
    @GetMapping("")
    fun getPoemByKeyword(@RequestParam("keyword", required = false) keyword: String?): ModelAndView {
        val poems = poetryMapper.findByTitleOrAuthor(keyword)
        val modelAndView = ModelAndView("/poems")
        modelAndView.addObject("poems", poems)
        modelAndView.addObject("keyword", keyword)
        return modelAndView
    }

    @GetMapping("/{uuid}")
    fun getPoemByUuid(@PathVariable("uuid") uuid: String): ModelAndView {
        val poem = poetryMapper.findByUuid(uuid)
        val modelAndView = ModelAndView("/poem")
        modelAndView.addObject("poem", poem)
        return modelAndView
    }
}
