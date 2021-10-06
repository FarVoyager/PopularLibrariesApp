package com.example.popularlibrariesapp

import android.app.Application
import com.example.popularlibrariesapp.dagger.AppComponent
import com.example.popularlibrariesapp.dagger.AppModule
import com.example.popularlibrariesapp.dagger.DaggerAppComponent
import com.example.popularlibrariesapp.model.room.Database
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

//    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
//    val navigatorHolder get() = cicerone.getNavigatorHolder()
//    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}