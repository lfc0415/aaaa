package com.pullo.story.exception

import org.springframework.http.HttpStatus

/**
 * @author by liufucheng on 2020/4/20
 */
class PulloError(var errorCode: String?, var statusCode: Int, var detail: String?) {
    fun errorCode(errorCode: String?): PulloError {
        return PulloError(errorCode, statusCode, detail)
    }

    fun statusCode(statusCode: Int): PulloError {
        return PulloError(errorCode, statusCode, detail)
    }

    fun detail(detail: String?): PulloError {
        return PulloError(errorCode, statusCode, detail)
    }

    fun toPulloException(): PulloException {
        return PulloException(detail)
            .errorCode(errorCode)
            .statusCode(statusCode)
    }

    fun toPulloException(message: String?): PulloException {
        return PulloException(message)
            .errorCode(errorCode)
            .statusCode(statusCode)
    }

    fun toPulloException(t: Throwable): PulloException {
        return PulloException(t.message, t)
            .errorCode(errorCode)
            .statusCode(statusCode)
    }

    fun toPulloException(message: String?, t: Throwable?): PulloException {
        return PulloException(message, t)
            .errorCode(errorCode)
            .statusCode(statusCode)
    }

    fun formatPulloException(message: String?, vararg args: Any?): PulloException {
        return PulloException(String.format(message!!, *args))
            .errorCode(errorCode)
            .statusCode(statusCode)
    }

    fun formatPulloException(message: String?, t: Throwable?, vararg args: Any?): PulloException {
        return PulloException(String.format(message!!, *args), t)
            .errorCode(errorCode)
            .statusCode(statusCode)
    }

    fun toPulloRuntimeException(): PulloRuntimeException {
        return toPulloException().toRuntimeException()
    }

    fun toPulloRuntimeException(message: String?): PulloRuntimeException? {
        return toPulloException(message).toRuntimeException()
    }

    fun toPulloRuntimeException(t: Throwable): PulloRuntimeException? {
        return toPulloException(t).toRuntimeException()
    }

    fun toPulloRuntimeException(message: String?, t: Throwable?): PulloRuntimeException? {
        return toPulloException(message, t).toRuntimeException()
    }

    fun formatPulloRuntimeException(message: String?, vararg args: Any?): PulloRuntimeException? {
        return formatPulloException(message, *args).toRuntimeException()
    }

    fun formatPulloRuntimeException(
        message: String?,
        t: Throwable?,
        vararg args: Any?
    ): PulloRuntimeException? {
        return formatPulloException(message, t, *args).toRuntimeException()
    }

    companion object {
        val NO_ERROR: PulloError =
            PulloError("", HttpStatus.OK.value(), "")
        /**
         * Unspecified default error.
         */
        val SYSTEM_ERROR: PulloError = PulloError(
            "global.error", HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "System error."
        )

        /**
         * The error happens in server side.
         */
        val SERVER_INTERNAL_ERROR: PulloError = PulloError(
            "global.server_error",
            HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server internal error."
        )

        /**
         * The service is stopped.
         */
        val SERVICE_STOPPED: PulloError = PulloError(
            "global.service_stopped",
            HttpStatus.SERVICE_UNAVAILABLE.value(), "Server is stopped."
        )

        /**
         * The server is busy.
         */
        val SERVER_BUSY: PulloError = PulloError(
            "global.server_busy", HttpStatus.SERVICE_UNAVAILABLE.value(),
            "Server is busy, please try later."
        )

        /**
         * The server is timeout.
         */
        val SERVER_TIMEOUT: PulloError = PulloError(
            "global.server_timeout", HttpStatus.GATEWAY_TIMEOUT.value(),
            "Server is timeout, please try later."
        )

        /**
         * Bad request.
         */
        val BAD_REQUEST: PulloError = PulloError(
            "global.bad_request", HttpStatus.BAD_REQUEST.value(),
            "Bad request."
        )

        /**
         * The request is forbidden.
         */
        val FORBIDDEN: PulloError = PulloError(
            "global.forbidden", HttpStatus.FORBIDDEN.value(),
            "Operation is forbidden."
        )

        /**
         * The request is not authorized.
         */
        val NOT_AUTHORIZED: PulloError = PulloError(
            "global.unauthorized", HttpStatus.UNAUTHORIZED.value(),
            "Operation is not authorized."
        )

        /**
         * The request resource is not found.
         */
        val NOT_FOUND: PulloError = PulloError(
            "global.not_found", HttpStatus.NOT_FOUND.value(),
            "Request resource is not found."
        )

        /**
         * The request method is not allowed.
         */
        val METHOD_NOT_ALLOWED: PulloError = PulloError(
            "global.method_not_allowed",
            HttpStatus.METHOD_NOT_ALLOWED.value(),
            "Request method is not allowed."
        )

        /**
         * The request is conflict with others.
         */
        val CONFLICT: PulloError = PulloError(
            "global.conflict", HttpStatus.CONFLICT.value(),
            "The request could not be completed due to a conflict with the current state of the target resource."
        )

        /**
         * The database error.
         */
        val DATABASE_ERROR: PulloError = PulloError(
            "global.database_error",
            HttpStatus.INTERNAL_SERVER_ERROR.value(), "Database error."
        )

        /**
         * The request has invalid argument.
         */
        val INVALID_ARGUMENT: PulloError = PulloError(
            "global.invalid_argument", HttpStatus.BAD_REQUEST.value(),
            "Invalid argument."
        )

        val VALUES: List<PulloError> = listOf(
            NO_ERROR,
            SYSTEM_ERROR,
            SERVER_INTERNAL_ERROR,
            SERVICE_STOPPED,
            SERVER_BUSY,
            BAD_REQUEST,
            FORBIDDEN,
            NOT_AUTHORIZED,
            NOT_FOUND,
            CONFLICT,
            DATABASE_ERROR,
            INVALID_ARGUMENT
        )
    }
}
