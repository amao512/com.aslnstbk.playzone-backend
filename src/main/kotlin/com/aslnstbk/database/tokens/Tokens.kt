package com.aslnstbk.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table() {
    private val id = Tokens.varchar("id", 50)
    private val login = Tokens.varchar("login", 25)
    private val token = Tokens.varchar("token", 50)

    fun insert(tokenDTO: TokenDTO) {
        try {
            transaction {
                Tokens.insert {
                    it[id] = tokenDTO.id
                    it[login] = tokenDTO.login
                    it[token] = tokenDTO.token
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}