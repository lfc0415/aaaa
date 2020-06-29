package com.pullo.story.exception

import com.pullo.story.log.Logging
import io.undertow.server.HttpServerExchange
import org.apache.logging.log4j.message.SimpleMessage
import javax.servlet.http.HttpServletRequest

object ExceptionHandlerHelper : Logging() {
    @JvmStatic
    fun logException(uri: String, e: Throwable, status: Int) {
        // Log all 5xx exceptions and other serious exceptions in ERROR level.
        if (status in 500..599) {
            log.error("$uri failed:", e)
        } else {
            log.warn("$uri failed:", e)
        }
    }

    @JvmStatic
    fun logPulloException(PulloException: PulloException) {
        val meta = PulloException.meta
        if (meta != null && meta.isNotEmpty()) {
            log.debug("exception meta: ", PulloException.meta)
        }
    }

    @JvmStatic
    fun logException(e: Throwable, request: HttpServletRequest, status: Int) {
        logException(request.requestURI, e, status)
        logHttpRequest(request)
    }

    @JvmStatic
    fun logException(e: Throwable, exchange: HttpServerExchange, status: Int) {
        logException(exchange.requestURI, e, status)
        logHttpRequest(exchange)
    }

    @JvmStatic
    fun createErrorResponse(
        requestId: String, errorCode: String?,
        message: String?, meta: Map<String, Any>?
    ): PulloErrorResponse {
        return PulloErrorResponse(
            listOf(generateCurrentError(requestId, errorCode, message, meta))
        )
    }

    private fun generateCurrentError(
        requestId: String, errorCode: String?, message: String?,
        meta: Map<String, Any>?
    ): PulloErrorBean {
        return PulloErrorBean().apply {
            code = errorCode
            detail = message

            this.meta = mutableMapOf<String, Any>().apply {
                put(PulloErrorBean.REQUEST_ID_FIELD, requestId)
                if (meta != null) {
                    putAll(meta)
                }
            }
        }
    }

    private fun logHttpRequest(exchange: HttpServerExchange) {
        log.debug {
            SimpleMessage(
                StringBuilder().apply {
                    append(exchange.requestMethod)
                    append(" ")
                    append(exchange.requestURI)
                    if (!exchange.queryString.isNullOrEmpty()) {
                        append("?")
                        append(exchange.queryString)
                    }
                    append(" ")
                    append(exchange.protocol)
                    append(" ")

                    exchange.requestHeaders?.forEach {
                        append("[")
                        append(it.headerName)
                        append(": ")
                        append(it.joinToString(","))
                        append("]")
                    }

                    append("[IGNORE REQUEST BODY]")
                }.toString()
            )
        }
    }

    private fun logHttpRequest(request: HttpServletRequest) {
        log.debug {
            SimpleMessage(
                StringBuilder().apply {
                    append(request.method)
                    append(" ")
                    append(request.requestURI)
                    if (!request.queryString.isNullOrEmpty()) {
                        append("?")
                        append(request.queryString)
                    }
                    append(" ")
                    append(request.protocol)
                    append(" ")

                    request.headerNames?.iterator()?.forEach {
                        append("[")
                        append(it)
                        append(": ")
                        append(request.getHeaders(it).toList().joinToString(","))
                        append("]")
                    }
                    append("[IGNORE REQUEST BODY]")
                }.toString()
            )
        }
    }
}
