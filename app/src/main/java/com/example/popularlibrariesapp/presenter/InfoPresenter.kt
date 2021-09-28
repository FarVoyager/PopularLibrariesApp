package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.model.GitHubUser
import com.example.popularlibrariesapp.view.InfoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class InfoPresenter(private val router: Router, private val user: GitHubUser): MvpPresenter<InfoView>() {
    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun setLoginHeader() {
        viewState.setLogin(user.login)
    }
}