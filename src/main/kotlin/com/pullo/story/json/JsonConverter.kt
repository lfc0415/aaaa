package com.pullo.story.json

import com.fasterxml.jackson.databind.JsonNode
import java.lang.reflect.Type

object JsonConverter {
    @JvmStatic
    fun <T> serialize(obj: T?): String {
        return JsonMapperFactory.defaultMapper.writeValueAsString(obj)
    }

    @JvmStatic
    fun <T> deserialize(json: String, clazz: Class<T>): T? {
        return JsonMapperFactory.defaultMapper.readValue(json, clazz)
    }

    inline fun <reified T> deserialize(json: String): T? {
        return deserialize(json, T::class.java)
    }

    @JvmStatic
    fun deserialize(json: String, type: Type): Any? {
        return JsonMapperFactory.defaultMapper.readValue<Any>(json, JsonMapperFactory.getType(type))
    }

    @JvmStatic
    fun <T> deserializeList(json: String, elementClazz: Class<T>): List<T>? {
        return JsonMapperFactory.defaultMapper.readValue<List<T>>(json, JsonMapperFactory.getListType(elementClazz))
    }

    inline fun <reified T> deserializeList(json: String): List<T>? {
        return deserializeList(json, T::class.java)
    }

    @JvmStatic
    fun <T, E> deserializeMap(json: String, key: Class<T>, value: Class<E>): Map<T, E>? {
        return JsonMapperFactory.defaultMapper.readValue<Map<T, E>>(json, JsonMapperFactory.getMapType(key, value))
    }

    @JvmStatic
    fun readTree(json: String): JsonNode? {
        return JsonMapperFactory.defaultMapper.readTree(json)
    }

    @JvmStatic
    fun <T> valueToTree(obj: T): JsonNode? {
        return JsonMapperFactory.defaultMapper.valueToTree(obj)
    }

    @JvmStatic
    fun <T> treeToValue(node: JsonNode, clazz: Class<T>): T? {
        return JsonMapperFactory.defaultMapper.treeToValue(node, clazz)
    }
}
