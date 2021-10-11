package com.example.popularlibrariesapp.model.room.cache

import com.example.popularlibrariesapp.model.network.GitHubRepository
import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.network.GitHubUsersRepo
import com.example.popularlibrariesapp.model.room.Database
import com.example.popularlibrariesapp.model.room.RoomGitHubRepository
import com.example.popularlibrariesapp.model.room.RoomGitHubUser
import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {
    fun getCachedRepositoriesByUser(user: GitHubUser): Single<List<GitHubRepository>>
    fun insertRepositoriesToCache(repos: List<RoomGitHubRepository>)
    fun getUserByLogin(text: String): RoomGitHubUser?
}