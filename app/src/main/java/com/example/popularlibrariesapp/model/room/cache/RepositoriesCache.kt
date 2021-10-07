package com.example.popularlibrariesapp.model.room.cache

import com.example.popularlibrariesapp.model.network.GitHubRepository
import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.room.Database
import com.example.popularlibrariesapp.model.room.RoomGitHubRepository
import com.example.popularlibrariesapp.model.room.RoomGitHubUser
import io.reactivex.rxjava3.core.Single
import java.lang.RuntimeException

class RepositoriesCache(private val db: Database): IRepositoriesCache {

    override fun getCachedRepositoriesByUser(user: GitHubUser): Single<List<GitHubRepository>> =
        Single.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("no such user in cache")
            db.repositoryDao.findForUser(roomUser.id).map { GitHubRepository(it.id, it.name) }
        }

    override fun insertRepositoriesToCache(repos: List<RoomGitHubRepository>) {
        db.repositoryDao.insert(repos)
    }

    override fun getUserByLogin(text: String): RoomGitHubUser? {
         return db.userDao.findByLogin(text)
    }
}