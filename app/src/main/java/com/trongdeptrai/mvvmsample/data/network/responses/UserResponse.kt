package com.trongdeptrai.mvvmsample.data.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.trongdeptrai.mvvmsample.data.db.entities.User

data class UserResponse(
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("message")
    @Expose
    val message: String?,
    @SerializedName("user")
    @Expose
    val user: User?
)