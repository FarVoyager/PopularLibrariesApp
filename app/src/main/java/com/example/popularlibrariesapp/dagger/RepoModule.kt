package com.example.popularlibrariesapp.dagger

import com.example.popularlibrariesapp.model.network.IDataSource
import com.example.popularlibrariesapp.model.network.IGitHubUsersRepo
import com.example.popularlibrariesapp.model.room.IGitHubRepositoriesRepo
import com.example.popularlibrariesapp.model.room.RetrofitGitHubRepositoriesRepo
import com.example.popularlibrariesapp.model.room.RetrofitGitHubUsersRepo
import com.example.popularlibrariesapp.model.room.cache.IRepositoriesCache
import com.example.popularlibrariesapp.model.room.cache.IUsersCache
import com.example.popularlibrariesapp.model.room.networkStatus.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api:IDataSource, networkStatus: INetworkStatus, cache: IUsersCache): IGitHubUsersRepo =
        RetrofitGitHubUsersRepo(api,networkStatus,cache)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache:IRepositoriesCache): IGitHubRepositoriesRepo =
        RetrofitGitHubRepositoriesRepo(api, networkStatus, cache)

}