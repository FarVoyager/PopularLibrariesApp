package com.example.popularlibrariesapp.presenter

import android.content.Intent
import android.os.Bundle
import com.example.popularlibrariesapp.model.GitHubUser
import com.example.popularlibrariesapp.model.GitHubUsersRepo
import com.example.popularlibrariesapp.recyclerView.IUserListPresenter
import com.example.popularlibrariesapp.recyclerView.UserItemView
import com.example.popularlibrariesapp.screens.AndroidScreens
import com.example.popularlibrariesapp.screens.IScreens
import com.example.popularlibrariesapp.view.UsersView
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import moxy.MvpPresenter

const val LOGIN_KEY = "LOGIN_KEY"

class UsersPresenter(private val usersRepo: GitHubUsersRepo, val router: Router, val screens: IScreens): MvpPresenter<UsersView>() {

    class UsersListPresenter: IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
        override fun getCount(): Int {
            return users.size
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            //переход на экран пользователя
            val bundle = Bundle()
            bundle.putString(LOGIN_KEY, usersRepo.getUsers()[itemView.pos].login)
            router.navigateTo(screens.info(bundle), true)
        }
    }

    private fun loadData() {
        val usersList = usersRepo.getUsers()
        usersListPresenter.users.addAll(usersList)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}