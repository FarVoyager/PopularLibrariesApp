package com.example.popularlibrariesapp.dagger

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.popularlibrariesapp.InfoFragment
import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.presenter.GITHUB_USER_KEY
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
class MiscModule {

    @Singleton
    @Provides
    fun selectedUser(fragment: Fragment) = fragment.arguments?.get(GITHUB_USER_KEY) as GitHubUser

    @Singleton
    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}