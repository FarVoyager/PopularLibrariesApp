package com.example.popularlibrariesapp.screens

import android.os.Bundle
import com.example.popularlibrariesapp.InfoFragment
import com.example.popularlibrariesapp.UsersFragment
import com.example.popularlibrariesapp.model.GitHubUser
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun info(user: GitHubUser): Screen {
        return FragmentScreen { InfoFragment.newInstance(user) }
    }

}