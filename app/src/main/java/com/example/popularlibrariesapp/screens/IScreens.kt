package com.example.popularlibrariesapp.screens

import android.os.Bundle
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun info(bundle: Bundle): Screen
}