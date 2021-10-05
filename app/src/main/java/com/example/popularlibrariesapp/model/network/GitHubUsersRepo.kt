package com.example.popularlibrariesapp.model.network

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitHubUsersRepo(private val api: IDataSource): IGitHubUsersRepo {
    override fun getUsers(): Single<List<GitHubUser>> {
        return api.getUsers().subscribeOn(Schedulers.io())
    }

    override fun getUserRepos(url: String): Single<List<GitHubRepository>> {
        return api.getUserRepos(url).subscribeOn(Schedulers.io())
    }
}