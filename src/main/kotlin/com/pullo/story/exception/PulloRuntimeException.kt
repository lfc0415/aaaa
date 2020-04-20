package com.pullo.story.exception

/**
 * @author by liufucheng on 2020/4/20
 */
class PulloRuntimeException : RuntimeException {
    constructor(message: String?) : super(message)
    
    constructor(cause: Throwable?) : super(cause)

    constructor(message: String?, cause: Throwable?) : super(message, cause)
}
