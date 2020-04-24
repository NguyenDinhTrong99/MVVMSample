package com.trongdeptrai.mvvmsample.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0;

@Entity
data class User (
    var id: Int? = null,
    var fullName: String? = null,
    var birthday: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var address: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}