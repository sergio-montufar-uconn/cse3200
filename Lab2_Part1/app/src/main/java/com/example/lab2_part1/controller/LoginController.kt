package com.example.lab2_part1.controller

import com.example.lab2_part1.model.LoginModel
class LoginController {
    private val loginModel: LoginModel = LoginModel(0, "default", "default")

    var id: Int
        get() = loginModel.id
        set(id) {
            loginModel.id = id
        }

    var username: String
        get() = loginModel.username
        set(user) {
            loginModel.username = user
        }

    var password: String
        get() = loginModel.password
        set(password) {
            loginModel.password = password
        }

}