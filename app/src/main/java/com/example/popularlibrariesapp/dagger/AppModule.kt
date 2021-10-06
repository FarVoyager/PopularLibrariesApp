package com.example.popularlibrariesapp.dagger

import com.example.popularlibrariesapp.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App = app
}