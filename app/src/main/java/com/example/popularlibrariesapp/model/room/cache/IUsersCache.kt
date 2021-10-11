package com.example.popularlibrariesapp.model.room.cache

import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.room.Database
import com.example.popularlibrariesapp.model.room.RoomGitHubUser
import io.reactivex.rxjava3.core.Single

interface IUsersCache {
    fun getCachedUsers(): Single<List<GitHubUser>>
    fun insertUsersToCache(users: List<RoomGitHubUser>)
}