package com.trongdeptrai.mvvmsample.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trongdeptrai.mvvmsample.data.network.repositories.UserRepositories

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val repository: UserRepositories) : ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}