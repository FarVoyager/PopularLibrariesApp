package com.example.popularlibrariesapp.model.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET ("/users")
    fun getUsers(): Single<List<GitHubUser>>

    @GET
    fun getUserRepos(@Url url: String): Single<List<UserRepo>>
}