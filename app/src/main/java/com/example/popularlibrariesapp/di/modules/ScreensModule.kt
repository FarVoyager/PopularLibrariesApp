package com.example.popularlibrariesapp.di.modules

import com.example.popularlibrariesapp.screens.AndroidScreens
import com.example.popularlibrariesapp.screens.IScreens
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ScreensModule {

    @Binds
    fun bindScreens(androidScreens: AndroidScreens): IScreens

}