package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.network.IGitHubUsersRepo
import com.example.popularlibrariesapp.model.network.UserRepo
import com.example.popularlibrariesapp.view.InfoView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
//репозитории пользователя получены, осталось отобразить
class InfoPresenter(
    private val uiScheduler: Scheduler,
    private val router: Router,
    private val user: GitHubUser,
    private val usersRepo: IGitHubUsersRepo
) : MvpPresenter<InfoView>() {

    val userRepos = mutableListOf<UserRepo>()
    private var compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun setLoginHeader() {
        user.login?.let { viewState.setLogin(it) }

    }
    private fun loadData() {
        val userReposRx = usersRepo.getUserRepos("/users/${user.login}/repos")
        userReposRx
            .observeOn(uiScheduler)
            .doOnSubscribe { d -> compositeDisposable.addAll(d) }
            .subscribe({
                userRepos.clear()
                userRepos.addAll(it)
                println(userRepos[0].toString() + " BEBT")

                println("onSuccess BEB")
            },
                {
                    println("onError: ${it.message}")
                })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }
}