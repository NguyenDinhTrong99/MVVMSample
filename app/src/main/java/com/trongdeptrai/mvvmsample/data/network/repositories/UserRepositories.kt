package com.trongdeptrai.mvvmsample.data.network.repositories

import com.trongdeptrai.mvvmsample.data.network.api.UserAPI
import com.trongdeptrai.mvvmsample.data.network.responses.SafeApiRequest
import com.trongdeptrai.mvvmsample.data.network.responses.UserResponse
import retrofit2.Response

class UserRepositories: SafeApiRequest() {

    suspend fun userLogin(username: String, password: String): UserResponse {
        return apiRequest{UserAPI().userLogin(username, password)}
    }
}