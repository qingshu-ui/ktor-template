package me.qingshu.ktorexample.config

import kotlinx.serialization.json.Json


internal val json by lazy {
    Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
}

@Suppress("FunctionName")
internal inline fun <reified T> GlobalJson(block: Json.() -> T): T {
    return json.block()
}