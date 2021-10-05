package com.example.popularlibrariesapp.model.room

import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.network.IDataSource
import com.example.popularlibrariesapp.model.network.IGitHubUsersRepo
import com.example.popularlibrariesapp.model.network.GitHubRepository
import com.example.popularlibrariesapp.model.room.networkStatus.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

//Практическое задание 1 - вытащить кэширование в отдельный класс RoomUserCache и внедрить его сюда через интерфейс IUserCache
class RetrofitGitHubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
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
                            db.userDao.insert(roomUsers)
                            users
                        }
                    }
            } else {
                println("Missing network connection, loading from local cache...")
                Single.fromCallable {
                    db.userDao.getAll().map { roomUser ->
                        GitHubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
                    }
                }
            }
        }.subscribeOn(Schedulers.io())

    override fun getUserRepos(url: String): Single<List<GitHubRepository>> {
        return api.getUserRepos(url).subscribeOn(Schedulers.io())
    }
}