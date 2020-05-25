package com.pullo.story.util

import java.io.File

/**
 * @author by liufucheng on 2020/4/23
 */
object FileHelper {
    fun getFilesByFolder(folderPath: String): List<File> {
        val fileFolder = File(folderPath)
        return fileFolder.listFiles().toList()
    }
}
