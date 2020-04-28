package com.trongdeptrai.mvvmsample.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trongdeptrai.mvvmsample.data.network.repositories.UserRepositories

@Suppress("UNCHECKED_CAST")
class UserModelFactory(private val repository: UserRepositories) : ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return UserModel(repository) as T
    }
}