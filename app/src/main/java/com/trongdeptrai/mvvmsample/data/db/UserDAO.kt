package com.trongdeptrai.mvvmsample.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trongdeptrai.mvvmsample.data.db.entities.CURRENT_USER_ID
import com.trongdeptrai.mvvmsample.data.db.entities.User

@Dao
interface UserDAO {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insert(vararg user: User)

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>
}