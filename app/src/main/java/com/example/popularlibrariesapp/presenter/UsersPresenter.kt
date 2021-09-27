package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.model.GitHubUser
import com.example.popularlibrariesapp.model.GitHubUsersRepo
import com.example.popularlibrariesapp.recyclerView.IUserListPresenter
import com.example.popularlibrariesapp.recyclerView.UserItemView
import com.example.popularlibrariesapp.screens.IScreens
import com.example.popularlibrariesapp.view.UsersView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

const val GITHUB_USER_KEY = "GITHUB_USER_KEY"

class UsersPresenter(private val usersRepo: GitHubUsersRepo, private val router: Router, private val screens: IScreens) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
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
            router.navigateTo(screens.info(usersRepo.getUsers()[itemView.pos]), true)
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