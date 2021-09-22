package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.view.InfoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class InfoPresenter(val router: Router): MvpPresenter<InfoView>() {
    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun setLoginHeader(text: String) {
        viewState.setLogin(text)
    }
}