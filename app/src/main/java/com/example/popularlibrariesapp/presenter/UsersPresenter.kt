package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.model.GitHubUser
import com.example.popularlibrariesapp.model.IGitHubUsersRepo
import com.example.popularlibrariesapp.recyclerView.IUserListPresenter
import com.example.popularlibrariesapp.recyclerView.UserItemView
import com.example.popularlibrariesapp.screens.IScreens
import com.example.popularlibrariesapp.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

const val GITHUB_USER_KEY = "GITHUB_USER_KEY"

class UsersPresenter(
    private val usersRepo: IGitHubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    private var compositeDisposable = CompositeDisposable()

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
            router.navigateTo(screens.info(usersListPresenter.users[itemView.pos]), true)
        }
    }

    private fun loadData() {

        val usersRx = usersRepo.getUsers()
        usersRx
            .doOnSubscribe { d -> compositeDisposable.addAll(d) }
            .subscribe({
                usersListPresenter.users.addAll(it)
                viewState.updateList()
                println("onSuccess")
            },
                {
                    println("onError: ${it.message}")
                })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        clearCompositeDisposable()
    }

    private fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }

}