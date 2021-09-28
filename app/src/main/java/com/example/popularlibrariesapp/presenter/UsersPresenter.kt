package com.example.popularlibrariesapp.presenter

import android.content.Intent
import android.os.Bundle
import com.example.popularlibrariesapp.model.GitHubUser
import com.example.popularlibrariesapp.model.GitHubUsersRepo
import com.example.popularlibrariesapp.model.IGitHubUsersRepo
import com.example.popularlibrariesapp.recyclerView.IUserListPresenter
import com.example.popularlibrariesapp.recyclerView.UserItemView
import com.example.popularlibrariesapp.screens.AndroidScreens
import com.example.popularlibrariesapp.screens.IScreens
import com.example.popularlibrariesapp.view.UsersView
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

const val GITHUB_USER_KEY = "GITHUB_USER_KEY"

class UsersPresenter(private val usersRepo: IGitHubUsersRepo, private val router: Router, private val screens: IScreens): MvpPresenter<UsersView>() {

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
            router.navigateTo(screens.info(usersRepo.getUsers()[itemView.pos]), true)
        }
    }

    private fun loadData() {
        var disposable: Disposable? = null
        Observable.fromIterable(usersRepo.getUsers())
            .doOnSubscribe { d ->
                disposable = d
            }
                .subscribe({
                    usersListPresenter.users.add(it)
                    println("onNext: user ${it.login} added!")
                },{
                    println("onError: error adding user to list!")
                }, {
                    disposable?.dispose()
                    println("onComplete: disposable is disposed!")
                })
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}