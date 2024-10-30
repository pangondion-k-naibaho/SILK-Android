package com.silk.client.ui.activity.authentication

interface FragmentsAuthCommunicator {
    fun executeLogin(email: String, password: String)

    fun executeRegister(
        firstName: String,
        lastName: String,
        ktp: String,
        email: String,
        phoneNum: String,
        password: String
    )

}