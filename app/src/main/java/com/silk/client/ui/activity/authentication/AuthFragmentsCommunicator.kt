package com.silk.client.ui.activity.authentication

interface AuthFragmentsCommunicator {
    fun getFormCompleteStatus(): Boolean

    fun onFormCompleted()

    fun setWarning()
}