package com.example.popularlibrariesapp.model.room.cache

import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.room.Database
import com.example.popularlibrariesapp.model.room.RoomGitHubUser
import io.reactivex.rxjava3.core.Single

class UsersCache: IUsersCache {

    private val db = Database.getInstance()
    override fun getCachedUsers(): Single<List<GitHubUser>> =
        Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GitHubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }

    override fun insertUsersToCache(users: List<RoomGitHubUser>) {
        db.userDao.insert(users)
    }
}