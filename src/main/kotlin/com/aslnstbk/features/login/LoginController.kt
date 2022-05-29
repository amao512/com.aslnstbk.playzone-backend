package com.aslnstbk.features.login

import com.aslnstbk.database.tokens.TokenDTO
import com.aslnstbk.database.tokens.Tokens
import com.aslnstbk.database.users.Users
import java.util.*

class LoginController {

    suspend fun login(
        loginReceiveRemote: LoginReceiveRemote,
        onBadRequest: suspend (String) -> Unit,
        onLogin: suspend (LoginResponseRemote) -> Unit
    ) {
        val userDTO = Users.fetchUser(loginReceiveRemote.login)

        userDTO?.let {
            val token = UUID.randomUUID().toString()

            Tokens.insert(
                tokenDTO = TokenDTO(
                    id = UUID.randomUUID().toString(),
                    login = loginReceiveRemote.login,
                    token = token
                )
            )

            onLogin(LoginResponseRemote(token = token))
        } ?: onBadRequest("User doesn't exists!")
    }
}