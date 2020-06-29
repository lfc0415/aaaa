package com.pullo.story.exception

class PulloErrorBean {
    var code: String? = null
    var detail: String? = null
    var meta: MutableMap<String, Any>? = null

    companion object {
        const val REQUEST_ID_FIELD = "requestId"
        const val SOURCE_FIELD = "source"
    }
}
