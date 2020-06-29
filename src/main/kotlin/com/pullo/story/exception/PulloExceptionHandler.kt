package com.pullo.story.exception

import com.pullo.story.exception.ExceptionHandlerHelper.createErrorResponse
import com.pullo.story.exception.ExceptionHandlerHelper.logException
import com.pullo.story.exception.ExceptionHandlerHelper.logPulloException
import com.pullo.story.log.Logging
import org.springframework.core.annotation.Order
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.sql.SQLException
import java.util.UUID
import java.util.concurrent.ThreadLocalRandom
import javax.servlet.http.HttpServletRequest
import javax.validation.ValidationException

@ControllerAdvice
@Order
@ResponseBody
class PulloExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(PulloException::class)
    fun handlePulloException(request: HttpServletRequest, ex: PulloException): ResponseEntity<PulloErrorResponse> {
        logException(ex, request, ex.statusCode)
        logPulloException(ex)
        return doHandlePulloException(ex)
    }

    @ExceptionHandler(SQLException::class, DataAccessException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleDataAccessException(request: HttpServletRequest, ex: DataAccessException): PulloErrorResponse {
        logException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR.value())

        // Here we do not expose database error to client.
        return createErrorResponse(
            getRequestId(),
            PulloError.DATABASE_ERROR.errorCode,
            PulloError.DATABASE_ERROR.detail, null
        )
    }

    @ExceptionHandler(NullPointerException::class, IllegalArgumentException::class, IllegalStateException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleRuntimeException(request: HttpServletRequest, ex: Exception): PulloErrorResponse {
        logException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR.value())

        return createErrorResponse(
            getRequestId(),
            PulloError.SERVER_INTERNAL_ERROR.errorCode,
            ex.message, null
        )
    }

    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintException(request: HttpServletRequest, ex: Exception): PulloErrorResponse {
        logException(ex, request, HttpStatus.BAD_REQUEST.value())

        return createErrorResponse(
            getRequestId(), null,
            ex.message, null
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(request: HttpServletRequest, ex: Exception): ResponseEntity<PulloErrorResponse> {
        logException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR.value())
        return ResponseEntity(
            createErrorResponse(
                getRequestId(),
                PulloError.SERVER_INTERNAL_ERROR.errorCode,
                ex.message, null
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    /**
     * Handle spring MVC internal exceptions with the same response format, without 'code' and request info.
     */
    override fun handleExceptionInternal(
        ex: Exception, body: Any?,
        headers: HttpHeaders, status: HttpStatus, request: WebRequest
    ): ResponseEntity<Any> {
        if (request is ServletWebRequest) {
            val servletRequest = request.request
            logException(ex, servletRequest, status.value())
        } else {
            log.warn(ex)
        }
        return ResponseEntity(
            createErrorResponse(
                getRequestId(), null,
                ex.message, null
            ),
            headers,
            status
        )
    }

    private fun getRequestId(): String {
        val random = ThreadLocalRandom.current()
        return UUID(random.nextLong(), random.nextLong()).toString()
    }

    private fun doHandlePulloException(ex: PulloException): ResponseEntity<PulloErrorResponse> {
        return ResponseEntity(
            createErrorResponse(
                getRequestId(),
                ex.errorCode,
                ex.message,
                ex.meta
            ),
            HttpStatus.valueOf(ex.statusCode)
        )
    }

    companion object : Logging()
}
