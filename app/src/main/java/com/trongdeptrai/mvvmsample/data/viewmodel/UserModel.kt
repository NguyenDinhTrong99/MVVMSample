package com.trongdeptrai.mvvmsample.data.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.trongdeptrai.mvvmsample.data.db.entities.AppDatabase
import com.trongdeptrai.mvvmsample.data.network.api.UserAPI
import com.trongdeptrai.mvvmsample.data.network.repositories.UserRepositories
import com.trongdeptrai.mvvmsample.ui.auth.AuthListener
import com.trongdeptrai.mvvmsample.utils.ApiException
import com.trongdeptrai.mvvmsample.utils.Coroutines
import com.trongdeptrai.mvvmsample.utils.NoInternetException
import java.lang.Exception

class UserModel(private val repository: UserRepositories): ViewModel() {
    var username: String? = ""
    var password: String? = ""
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if(username.isNullOrEmpty() || password.isNullOrEmpty()) {
                authListener?.onLoginFailure("Invalid username or password $username $password")
            return
        }
        Coroutines.main {
            try {
                val response = repository.userLogin(username!!, password!!)
                response.user?.let {
                    authListener?.onLoginSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                    authListener?.onLoginFailure(response.message!!)
            }catch (e: ApiException) {
                authListener?.onLoginFailure(e.message!!)
            }catch (e: NoInternetException) {
                authListener?.onLoginFailure(e.message!!)
            }
        }
    }
}