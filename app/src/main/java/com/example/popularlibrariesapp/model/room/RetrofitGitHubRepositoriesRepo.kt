package com.example.popularlibrariesapp.model.room

import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.network.IDataSource
import com.example.popularlibrariesapp.model.network.GitHubRepository
import com.example.popularlibrariesapp.model.room.cache.IRepositoriesCache
import com.example.popularlibrariesapp.model.room.networkStatus.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RetrofitGitHubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database,
    private val reposCache: IRepositoriesCache,
) : IGitHubRepositoriesRepo {
    override fun getRepositories(user: GitHubUser): Single<List<GitHubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getUserRepos(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomUser = user.login?.let {
                                    db.userDao.findByLogin(it)
                                } ?: throw RuntimeException("No such user in cache")
                                val roomRepos = repositories.map {
                                    RoomGitHubRepository(it.id ?: "", it.name ?: "", roomUser.id)
                                }
                                reposCache.insertRepositoriesToCache(roomRepos)
                                repositories
                            }
                        }
                } ?: Single.error<List<GitHubRepository>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())
            } else {
                println("Missing network connection, loading from local cache...")
                reposCache.getCachedRepositoriesByUser(user)
            }
        }.subscribeOn(Schedulers.io())
}