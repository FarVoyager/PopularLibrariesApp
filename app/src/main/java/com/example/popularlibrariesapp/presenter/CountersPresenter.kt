package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.model.GitHubUser
import com.example.popularlibrariesapp.model.GitHubUsersRepo
import com.example.popularlibrariesapp.recyclerView.IUserListPresenter
import com.example.popularlibrariesapp.recyclerView.UserItemView
import com.example.popularlibrariesapp.screens.IScreens
import com.example.popularlibrariesapp.view.CountersView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class CountersPresenter(val router: Router, val screens: IScreens): MvpPresenter<CountersView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}