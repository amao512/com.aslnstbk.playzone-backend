package com.aslnstbk.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRegisterRouging() {
    val registerController = RegisterController()

    routing {
        post("/register") {
            val register = call.receive(RegisterReceiveRemote::class)

            registerController.registerUser(
                registerReceiveRemote = register,
                onBadRequest = {
                    call.respond(HttpStatusCode.BadRequest, it)
                }, onRegister = {
                    call.respond(it)
                }
            )
        }
    }
}