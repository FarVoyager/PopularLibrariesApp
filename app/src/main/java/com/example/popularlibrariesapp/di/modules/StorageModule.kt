package com.example.popularlibrariesapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.popularlibrariesapp.model.room.Database
import com.example.popularlibrariesapp.model.room.networkStatus.AndroidNetworkStatus
import com.example.popularlibrariesapp.model.room.networkStatus.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun bindDatabase(context: Context): Database = Room.databaseBuilder(context, Database::class.java, "database")
        .build()

    @Provides
    fun bindNetworkStatus(context: Context): INetworkStatus = AndroidNetworkStatus(context)

}