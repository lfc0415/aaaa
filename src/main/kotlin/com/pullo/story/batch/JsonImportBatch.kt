package com.pullo.story.batch

import com.pullo.story.json.JsonConverter
import com.pullo.story.model.dto.PoetryDTO
import com.pullo.story.model.entity.Poetry
import com.pullo.story.mybatis.mapper.PoetryMapper
import com.pullo.story.util.FileHelper
import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Component
import java.io.File

/**
 * @author by liufucheng on 2020/4/23
 */
@Component
class JsonImportBatch(
    private val poetryMapper: PoetryMapper
) {
    val logger = LoggerFactory.getLogger(JsonImportBatch::class.java)
    var count = 0

    fun getAllFiles(): List<File> {
        return FileHelper.getFilesByFolder("/Users/liufucheng/work/study/chinese-poetry/json")
    }

    fun reader() {
        val poetryList = mutableListOf<PoetryDTO>()
        getAllFiles().forEach {
            val jsonStr = FileUtils.readFileToString(it, "UTF-8")
            try {
                val poetryDTOs = JsonConverter.deserializeList<PoetryDTO>(jsonStr)
                if (!poetryDTOs.isNullOrEmpty()) {
                    poetryList.addAll(poetryDTOs)
                }
                write(poetryList)
            } catch (e: Exception) {
                logger.error("解析json文件出错！" + it.absolutePath)
            } finally {
                poetryList.clear()
            }
        }
    }

    fun write(poetryList: List<PoetryDTO>) {
        poetryList.forEach {

            try {
                logger.info("insert" + count++ + "::" + JsonConverter.serialize(it))
                poetryMapper.insert(Poetry().apply {
                    BeanUtils.copyProperties(it, this)
                })
            } catch (e: Exception) {
                logger.error("insert error ::" + JsonConverter.serialize(it))
            }
        }
    }
}
