package com.silk.client.ui.activity.authentication

interface FragmentsAuthCommunicator {
    fun startLoading()

    fun stopLoading()

    fun executeLogin(email: String, password: String)

}