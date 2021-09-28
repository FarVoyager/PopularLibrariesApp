package com.example.popularlibrariesapp.model

class GitHubUsersRepo {

    private val repositories = listOf(
        GitHubUser("DarthVaderShinobi"),
        GitHubUser("MiwkaUbivawka"),
        GitHubUser("FastAsHeckBoi"),
        GitHubUser("Onotole1"),
        GitHubUser("LexaPetrov666")
        )

    fun getUsers(): List<GitHubUser> {
        return repositories
    }
}