package com.aslnstbk.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {
    val loginController = LoginController()

    routing {
        post("/login") {
            val login = call.receive(LoginReceiveRemote::class)

            loginController.login(
                loginReceiveRemote = login,
                onBadRequest = {
                    call.respond(HttpStatusCode.BadRequest, it)
                },
                onLogin = {
                    call.respond(it)
                }
            )
        }
    }
}