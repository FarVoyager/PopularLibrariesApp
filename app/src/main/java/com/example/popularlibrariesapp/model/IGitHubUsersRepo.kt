package com.example.popularlibrariesapp.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

interface IGitHubUsersRepo {
    fun getUsers(): Single<List<GitHubUser>>
}