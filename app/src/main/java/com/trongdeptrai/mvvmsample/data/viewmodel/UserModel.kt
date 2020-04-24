package com.trongdeptrai.mvvmsample.data.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.trongdeptrai.mvvmsample.data.network.repositories.UserRepositories
import com.trongdeptrai.mvvmsample.ui.auth.AuthListener
import com.trongdeptrai.mvvmsample.utils.ApiException
import com.trongdeptrai.mvvmsample.utils.Coroutines
import java.lang.Exception

class UserModel: ViewModel() {
    var username: String? = ""
    var password: String? = ""
    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if(username.isNullOrEmpty() || password.isNullOrEmpty()) {
                authListener?.onLoginFailure("Invalid username or password $username $password")
            return
        }
        Coroutines.main {
            try {
                val response = UserRepositories().userLogin(username!!, password!!)
                response.user?.let {
                    authListener?.onLoginSuccess(it)
                    return@main
                }
                    authListener?.onLoginFailure(response.message!!)
            }catch (e: ApiException) {
                authListener?.onLoginFailure(e.message!!)
            }
        }
    }
}