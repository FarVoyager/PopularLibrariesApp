package com.example.popularlibrariesapp.di.modules

import com.example.popularlibrariesapp.MainActivity
import com.example.popularlibrariesapp.UsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

}