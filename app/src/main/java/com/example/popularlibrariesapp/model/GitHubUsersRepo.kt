package com.example.popularlibrariesapp.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class GitHubUsersRepo : IGitHubUsersRepo {

    private val repositories = listOf(
            GitHubUser("DarthVaderShinobi"),
            GitHubUser("MiwkaUbivawka"),
            GitHubUser("FastAsHeckBoi"),
            GitHubUser("Onotole1"),
            GitHubUser("LexaPetrov666")
    )

    override fun getUsers(): Single<List<GitHubUser>> {
        return Single.just(repositories)
    }
}