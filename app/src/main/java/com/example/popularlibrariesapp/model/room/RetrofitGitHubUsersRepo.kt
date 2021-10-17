package com.example.popularlibrariesapp.model.room

import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.network.IDataSource
import com.example.popularlibrariesapp.model.network.IGitHubUsersRepo
import com.example.popularlibrariesapp.model.network.GitHubRepository
import com.example.popularlibrariesapp.model.room.cache.IUsersCache
import com.example.popularlibrariesapp.model.room.networkStatus.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitGitHubUsersRepo @Inject constructor(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val usersCache: IUsersCache,
    ): IGitHubUsersRepo {
    override fun getUsers(): Single<List<GitHubUser>> =
        networkStatus.isOnlineSingle().flatMap {
            isOnline ->
            if (isOnline) {
                api.getUsers()
                    .flatMap { users ->
                        Single.fromCallable {
                            val roomUsers = users.map { user ->
                                RoomGitHubUser(
                                    user.id ?: "",
                                    user.login ?: "",
                                    user.avatarUrl ?: "",
                                    user.reposUrl ?: "") }
                            usersCache.insertUsersToCache(roomUsers)
                            users
                        }
                    }
            } else {
                println("Missing network connection, loading from local cache...")
                usersCache.getCachedUsers()
            }
        }.subscribeOn(Schedulers.io())

    override fun getUserRepos(url: String): Single<List<GitHubRepository>> {
        return api.getUserRepos(url).subscribeOn(Schedulers.io())
    }
}