package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.model.CountersModel
import com.example.popularlibrariesapp.model.GitHubUser
import com.example.popularlibrariesapp.model.GitHubUsersRepo
import com.example.popularlibrariesapp.recyclerView.IUserListPresenter
import com.example.popularlibrariesapp.recyclerView.UserItemView
import com.example.popularlibrariesapp.view.CountersView
import moxy.InjectViewState
import moxy.MvpPresenter

class CountersPresenter(private val usersRepo: GitHubUsersRepo): MvpPresenter<CountersView>() {

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
        }
    }

    private fun loadData() {
        val usersList = usersRepo.getUsers()
        usersListPresenter.users.addAll(usersList)
        viewState.updateList()
    }

}