package com.example.stagetv.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stagetv.data.db.dao.UserDao
import com.example.stagetv.data.db.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}