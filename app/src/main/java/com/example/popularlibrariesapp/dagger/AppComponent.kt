package com.example.popularlibrariesapp.dagger

import com.example.popularlibrariesapp.InfoFragment
import com.example.popularlibrariesapp.MainActivity
import com.example.popularlibrariesapp.UsersFragment
import com.example.popularlibrariesapp.presenter.MainPresenter
import com.example.popularlibrariesapp.presenter.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CacheModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)

    fun inject(usersFragment: UsersFragment)
    fun inject(infoFragment: InfoFragment)
}