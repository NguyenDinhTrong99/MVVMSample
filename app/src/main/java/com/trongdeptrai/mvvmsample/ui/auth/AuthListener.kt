package com.trongdeptrai.mvvmsample.ui.auth

import com.trongdeptrai.mvvmsample.data.db.entities.User

interface AuthListener {
    fun onStarted();
    fun onLoginSuccess(user: User)
    fun onLoginFailure(message: String)
}