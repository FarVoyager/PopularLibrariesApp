package com.example.popularlibrariesapp.di.modules

import com.example.popularlibrariesapp.InfoFragment
import com.example.popularlibrariesapp.UsersFragment
import com.example.popularlibrariesapp.model.network.GitHubUsersRepo
import com.example.popularlibrariesapp.model.network.IGitHubUsersRepo
import com.example.popularlibrariesapp.model.room.IGitHubRepositoriesRepo
import com.example.popularlibrariesapp.model.room.RetrofitGitHubRepositoriesRepo
import com.example.popularlibrariesapp.model.room.RetrofitGitHubUsersRepo
import com.example.popularlibrariesapp.model.room.cache.IRepositoriesCache
import com.example.popularlibrariesapp.model.room.cache.IUsersCache
import com.example.popularlibrariesapp.model.room.cache.RepositoriesCache
import com.example.popularlibrariesapp.model.room.cache.UsersCache
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UserModule {

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindInfoFragment(): InfoFragment

    @Binds
    fun bindGitHubUsersRepo(gitHubUsersRepo: RetrofitGitHubUsersRepo): IGitHubUsersRepo

    @Binds
    fun bindGitHubRepositoriesRepo(gitHubRepositoriesRepo: RetrofitGitHubRepositoriesRepo): IGitHubRepositoriesRepo

    @Binds
    fun bindUsersCache(usersCache: UsersCache): IUsersCache

    @Binds
    fun bindRepositoriesCache(repositoriesCache: RepositoriesCache): IRepositoriesCache

}