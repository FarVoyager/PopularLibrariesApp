package com.example.popularlibrariesapp.presenter

import android.widget.Toast
import com.example.popularlibrariesapp.model.GitHubUser
import com.example.popularlibrariesapp.model.GitHubUsersRepo
import com.example.popularlibrariesapp.view.InfoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import kotlin.math.log

class InfoPresenter(private val router: Router, private val user: GitHubUser?): MvpPresenter<InfoView>() {
    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        setLoginHeader()
    }

    fun setLoginHeader() {
        if (user != null) {
            viewState.setLogin(user.login)
        } else {
            println("login is null BEB")
        }
    }
}