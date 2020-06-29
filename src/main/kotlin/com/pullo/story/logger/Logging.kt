package com.pullo.story.logger

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.reflect.full.companionObject

open class Logging {
    val log: Logger = getLogger(this.javaClass)

    private fun <T> unwrapCompanionClass(clazz: Class<T>): Class<*> {
        return if (clazz.enclosingClass?.kotlin?.companionObject?.java == clazz) {
            clazz.enclosingClass
        } else {
            clazz
        }
    }

    private fun <T> getLogger(clazz: Class<T>): Logger {
        return LogManager.getLogger(unwrapCompanionClass(clazz).simpleName)
    }
}
