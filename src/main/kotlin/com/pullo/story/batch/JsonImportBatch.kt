package com.pullo.story.batch

import com.pullo.story.json.JsonConverter
import com.pullo.story.model.dto.PoetryDTO
import com.pullo.story.model.entity.Poetry
import com.pullo.story.mybatis.mapper.PoetryMapper
import com.pullo.story.util.FileHelper
import org.apache.commons.io.FileUtils
import org.apache.ibatis.session.ExecutorType
import org.apache.ibatis.session.SqlSessionFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Component
import java.io.File

/**
 * @author by liufucheng on 2020/4/23
 */
@Component
class JsonImportBatch(
    private val sqlSessionFactory: SqlSessionFactory
) {
    val logger = LoggerFactory.getLogger(JsonImportBatch::class.java)

    fun getAllFiles(): List<File> {
        return FileHelper.getFilesByFolder("/Users/liufucheng/work/study/chinese-poetry/json")
    }

    fun reader() {
        val poetryList = mutableListOf<PoetryDTO>()
        getAllFiles().forEach {
            val jsonStr = FileUtils.readFileToString(it, "UTF-8")
            val poetryDTOs = JsonConverter.deserializeList<PoetryDTO>(jsonStr)
            if (!poetryDTOs.isNullOrEmpty()) {
                poetryList.addAll(poetryDTOs)
            }
            write(poetryList)
            poetryList.clear()
        }
    }

    fun write(poetryList: List<PoetryDTO>) {
        val sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)
        val poetryMapper = sqlSession.getMapper(PoetryMapper::class.java)
        var count = 0
        poetryList.forEach {
            logger.info("insert" + JsonConverter.serialize(it))
            poetryMapper.insert(Poetry().apply {
                BeanUtils.copyProperties(it, this)
            })
            count++
            if (count > 1000) {
                sqlSession.commit()
                count = 0
            }
        }
        sqlSession.commit()
    }
}
