package com.example.stagetv.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.stagetv.data.db.entity.User

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Query("SELECT * FROM user WHERE uid = :uid")
    suspend fun getUserByUid(uid: String): User?

    @Query("DELETE FROM user")
    suspend fun deleteUser()

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getSingleUser(): User?
}