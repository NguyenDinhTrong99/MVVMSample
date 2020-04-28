package com.trongdeptrai.mvvmsample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.trongdeptrai.mvvmsample.R
import com.trongdeptrai.mvvmsample.data.db.entities.User
import com.trongdeptrai.mvvmsample.data.viewmodel.UserModel
import com.trongdeptrai.mvvmsample.data.viewmodel.UserModelFactory
import com.trongdeptrai.mvvmsample.databinding.ActivityLoginBinding
import com.trongdeptrai.mvvmsample.ui.home.HomeActivity
import com.trongdeptrai.mvvmsample.utils.hide
import com.trongdeptrai.mvvmsample.utils.show
import com.trongdeptrai.mvvmsample.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()
    private  val  factory: UserModelFactory by instance<UserModelFactory>()
    private lateinit var viewModel: UserModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(UserModel::class.java)
        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.modelview = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })


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
