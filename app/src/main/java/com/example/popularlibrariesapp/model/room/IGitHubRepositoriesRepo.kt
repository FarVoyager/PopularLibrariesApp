package com.example.popularlibrariesapp.model.room

import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.network.GitHubRepository
import io.reactivex.rxjava3.core.Single

interface IGitHubRepositoriesRepo {
    fun getRepositories(user: GitHubUser): Single<List<GitHubRepository>>
}