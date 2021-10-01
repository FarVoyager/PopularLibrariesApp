package com.example.popularlibrariesapp.model.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {
    @GET ("/users")
    fun getUsers(): Single<List<GitHubUser>>
}