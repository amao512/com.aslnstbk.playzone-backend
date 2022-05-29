package com.aslnstbk.features.register

import com.aslnstbk.database.tokens.TokenDTO
import com.aslnstbk.database.tokens.Tokens
import com.aslnstbk.database.users.UserDTO
import com.aslnstbk.database.users.Users
import com.aslnstbk.utils.isValidEmail
import java.util.*

class RegisterController {

     suspend fun registerUser(
        registerReceiveRemote: RegisterReceiveRemote,
        onBadRequest: suspend (String) -> Unit,
        onRegister: suspend (RegisterResponseRemote) -> Unit
    ) {
        val userDTO = Users.fetchUser(registerReceiveRemote.login)

        if (userDTO != null) {
            return onBadRequest("User already exists!")
        }

        if (!registerReceiveRemote.email.isValidEmail()) {
            return onBadRequest( "Email is not valid!")
        }

        val token = UUID.randomUUID().toString()

        Users.insert(
            userDTO = UserDTO(
                login = registerReceiveRemote.login,
                email = registerReceiveRemote.email,
                password = registerReceiveRemote.password,
                username = ""
            )
        )

        Tokens.insert(tokenDTO = TokenDTO(
            id = UUID.randomUUID().toString(),
            login = registerReceiveRemote.login,
            token = token
        ))

        onRegister(RegisterResponseRemote(token = token))
    }
}