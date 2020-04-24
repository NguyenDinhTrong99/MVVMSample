package com.trongdeptrai.mvvmsample.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.trongdeptrai.mvvmsample.R
import com.trongdeptrai.mvvmsample.data.db.entities.User
import com.trongdeptrai.mvvmsample.data.viewmodel.UserModel
import com.trongdeptrai.mvvmsample.databinding.ActivityLoginBinding
import com.trongdeptrai.mvvmsample.utils.hide
import com.trongdeptrai.mvvmsample.utils.show
import com.trongdeptrai.mvvmsample.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {
    private lateinit var viewModel: UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserModel::class.java)
        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.modelview = viewModel
        viewModel.authListener = this

    }

    override fun onStarted() {
        progressBarLogin.show()
    }

    override fun onLoginSuccess(user: User) {
        progressBarLogin.hide()
        layoutContainer.snackbar("${user.fullName} is logger")
    }

    override fun onLoginFailure(message: String) {
        progressBarLogin.hide()
        layoutContainer.snackbar(message)
    }
}
