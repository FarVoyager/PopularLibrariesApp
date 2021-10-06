package com.example.popularlibrariesapp.screens

import com.example.popularlibrariesapp.InfoFragment
import com.example.popularlibrariesapp.UsersFragment
import com.example.popularlibrariesapp.model.network.GitHubUser
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class AndroidScreens @Inject constructor() : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun info(user: GitHubUser): Screen {
        return FragmentScreen { InfoFragment.newInstance(user) }
    }

}