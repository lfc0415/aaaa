package com.pullo.story.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Authorize test demo
 *
 * @author liufucheng
 */
@RestController
@RequestMapping("/tasks")
class TaskController {
    @GetMapping
    fun listTasks(): String {
        return "任务列表"
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun newTasks(): String {
        return "创建了一个新的任务"
    }

    @PutMapping("/{taskId}")
    fun updateTasks(@PathVariable("taskId") id: Int): String {
        return "更新了一下id为:" + id + "的任务"
    }

    @DeleteMapping("/{taskId}")
    fun deleteTasks(@PathVariable("taskId") id: Int): String {
        return "删除了id为:" + id + "的任务"
    }
}
