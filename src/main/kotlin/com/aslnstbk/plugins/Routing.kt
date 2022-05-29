package com.aslnstbk.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val text: String
)

fun Application.configureRouting() {

    routing {
        get("/") {
            val list = listOf(Test(text = "Hello, World!"), Test(text = "Hello, World 2"))
            call.respond(list)
        }
    }
}
