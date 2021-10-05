package com.example.popularlibrariesapp.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.RuntimeException


@Database(entities = [RoomGitHubUser::class, RoomGitHubRepository::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: com.example.popularlibrariesapp.model.room.Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context,com.example.popularlibrariesapp.model.room.Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}