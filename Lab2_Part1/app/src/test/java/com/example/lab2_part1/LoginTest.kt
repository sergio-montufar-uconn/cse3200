package com.example.lab2_part1

import com.example.lab2_part1.controller.LoginController
import com.example.lab2_part1.model.LoginModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class LoginTest {
    private lateinit var loginModel: LoginModel
    private lateinit var loginController: LoginController

    @Before
    fun setup() {
        loginModel = LoginModel(null, "", "")
    }

    @Test
    fun userIdTest() {
        loginController.id = 69
        assertEquals(69, loginController.id)
    }

    @Test
    fun usernameTest() {
        loginController.username = "bingus"
        assertEquals("bingus", loginController.username)
    }

    @Test
    fun passwordTest() {
        loginController.password = "bongus"
        assertEquals("bongus", loginController.password)
    }









}