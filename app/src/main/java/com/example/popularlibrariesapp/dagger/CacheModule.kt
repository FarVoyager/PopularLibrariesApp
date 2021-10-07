package com.example.popularlibrariesapp.dagger

import androidx.room.Room
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.model.room.Database
import com.example.popularlibrariesapp.model.room.cache.IRepositoriesCache
import com.example.popularlibrariesapp.model.room.cache.IUsersCache
import com.example.popularlibrariesapp.model.room.cache.RepositoriesCache
import com.example.popularlibrariesapp.model.room.cache.UsersCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app,
    Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IUsersCache = UsersCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IRepositoriesCache = RepositoriesCache(database)
}