package com.pullo.story.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.joda.time.DateTime
import java.io.IOException

class CstDateTimeSerializer : JsonSerializer<DateTime>() {
    @Throws(IOException::class)
    override fun serialize(
        dateTime: DateTime, jsonGenerator: JsonGenerator,
        provider: SerializerProvider
    ) {
        jsonGenerator.writeString(dateTime.toString())
    }

    override fun handledType(): Class<DateTime> {
        return DateTime::class.java
    }
}
