package com.pullo.story.exception

class PulloException : Exception {
    var statusCode: Int = PulloError.SERVER_INTERNAL_ERROR.statusCode
    var requestId: String? = null
    var errorCode: String? = null
    var meta: Map<String, Any>? = null

    constructor() : super()

    constructor(message: String?) : super(message)

    constructor(cause: Throwable?) : super(cause)

    constructor(message: String?, cause: Throwable?) : super(message, cause)

    fun requestId(requestId: String?): PulloException {
        this.requestId = requestId
        return this
    }

    fun errorCode(errorCode: String?): PulloException {
        this.errorCode = errorCode
        return this
    }

    fun statusCode(statusCode: Int): PulloException {
        this.statusCode = statusCode
        return this
    }

    fun meta(meta: Map<String, Any>?): PulloException {
        this.meta = meta
        return this
    }

    fun toRuntimeException(): PulloRuntimeException {
        return PulloRuntimeException(this)
    }

    override fun toString(): String {
        val builder = StringBuilder(javaClass.name)
        builder.append(": ")
        builder.append("(")
        builder.append(statusCode)
        builder.append(", ")
        builder.append(errorCode)
        builder.append(") ")
        builder.append(localizedMessage)
        return builder.toString()
    }
}
