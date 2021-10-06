package com.example.popularlibrariesapp.di

import android.content.Context
import com.example.popularlibrariesapp.screens.AndroidScreens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpAppCompatFragment
import javax.inject.Inject

abstract class AbsFragment(): MvpAppCompatFragment(), HasAndroidInjector {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var androidScreens: AndroidScreens
    @Inject
    lateinit var scheduler: Scheduler


    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}