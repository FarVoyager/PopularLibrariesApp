package com.example.popularlibrariesapp

import android.app.Application
import com.example.popularlibrariesapp.di.DaggerAppComponent
import com.example.popularlibrariesapp.model.room.Database
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> =
       DaggerAppComponent.builder()
           .withScheduler(AndroidSchedulers.mainThread())
           .withContext(applicationContext).apply {
               val cicerone = Cicerone.create()
               withRouter(cicerone.router)
               withNavigatorHolder(cicerone.getNavigatorHolder())
           }
           .build()


    companion object {
        lateinit var instance: App
    }

    //Временно до Даггера положим это тут
//    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
//    val navigatorHolder get() = cicerone.getNavigatorHolder()
//    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
    }


}