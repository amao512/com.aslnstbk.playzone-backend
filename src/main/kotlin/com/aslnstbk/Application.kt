package com.aslnstbk

import com.aslnstbk.features.login.configureLoginRouting
import com.aslnstbk.features.register.configureRegisterRouging
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import com.aslnstbk.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/playzone", driver = "org.postgresql.Driver",
    "postgres", "bin12216$"
        )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureLoginRouting()
        configureRegisterRouging()
        configureSerialization()
    }.start(wait = true)
}
