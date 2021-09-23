package com.example.popularlibrariesapp.model

interface IGitHubUsersRepo {
    fun getUsers(): List<GitHubUser>
}