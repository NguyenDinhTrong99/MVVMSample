package com.trongdeptrai.mvvmsample.data.network.repositories

import com.trongdeptrai.mvvmsample.data.db.entities.AppDatabase
import com.trongdeptrai.mvvmsample.data.db.entities.User
import com.trongdeptrai.mvvmsample.data.network.api.UserAPI
import com.trongdeptrai.mvvmsample.data.network.responses.SafeApiRequest
import com.trongdeptrai.mvvmsample.data.network.responses.UserResponse
import retrofit2.Response

class UserRepositories(private val api: UserAPI,
private val db: AppDatabase): SafeApiRequest() {

    suspend fun userLogin(username: String, password: String): UserResponse {
        return apiRequest{api.userLogin(username, password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().insert(user)

    fun getUser() = db.getUserDao().getUser()
}