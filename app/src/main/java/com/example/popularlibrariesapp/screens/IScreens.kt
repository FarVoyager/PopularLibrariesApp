package com.example.popularlibrariesapp.screens

import com.example.popularlibrariesapp.model.network.GitHubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun info(user: GitHubUser): Screen
}