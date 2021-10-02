package com.example.popularlibrariesapp.model.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Url

interface IGitHubUsersRepo {
    fun getUsers(): Single<List<GitHubUser>>
    fun getUserRepos(url: String): Single<List<UserRepo>>
}