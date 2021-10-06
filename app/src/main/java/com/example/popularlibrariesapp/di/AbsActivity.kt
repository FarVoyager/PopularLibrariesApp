package com.example.popularlibrariesapp.di

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import com.example.popularlibrariesapp.screens.AndroidScreens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import javax.inject.Inject

abstract class AbsActivity(): MvpAppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var androidScreens: AndroidScreens
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}