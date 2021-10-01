package com.example.popularlibrariesapp.model.network

import io.reactivex.rxjava3.core.Single

interface IGitHubUsersRepo {
    fun getUsers(): Single<List<GitHubUser>>
}