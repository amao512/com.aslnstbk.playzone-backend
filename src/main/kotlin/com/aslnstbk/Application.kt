package com.aslnstbk

import com.aslnstbk.features.login.configureLoginRouting
import com.aslnstbk.features.register.configureRegisterRouging
import io.ktor.server.engine.*
import com.aslnstbk.plugins.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main() {
//    val config = HikariConfig("hikari.properties")
//    val dataSource = HikariDataSource(config)
//    Database.connect(dataSource)
//
//    Database.connect("jdbc:postgresql://localhost:5432/playzone", driver = "org.postgresql.Driver",
//    "postgres", "bin12216$"
//        )

    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureRouting()
//        configureLoginRouting()
//        configureRegisterRouging()
//        configureSerialization()
    }.start(wait = true)
}
