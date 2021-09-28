package com.example.popularlibrariesapp.screens

import android.os.Bundle
import com.example.popularlibrariesapp.model.GitHubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun info(user: GitHubUser): Screen
}