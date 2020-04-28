package com.trongdeptrai.mvvmsample.ui.profile

import androidx.lifecycle.ViewModel
import com.trongdeptrai.mvvmsample.data.network.repositories.UserRepositories

class ProfileViewModel(
    repository: UserRepositories
): ViewModel() {
    val user = repository.getUser()
}