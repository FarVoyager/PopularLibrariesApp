package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.network.IGitHubUsersRepo
import com.example.popularlibrariesapp.model.network.GitHubRepository
import com.example.popularlibrariesapp.model.room.IGitHubRepositoriesRepo
import com.example.popularlibrariesapp.recyclerView.reposRecyclerView.IRepoListPresenter
import com.example.popularlibrariesapp.recyclerView.reposRecyclerView.RepoItemView
import com.example.popularlibrariesapp.view.InfoView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

//репозитории пользователя получены, осталось отобразить
class InfoPresenter(private val user: GitHubUser) : MvpPresenter<InfoView>() {

    @Inject lateinit var reposRepo: IGitHubRepositoriesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var scheduler: Scheduler

    private var compositeDisposable = CompositeDisposable()

    class RepoListPresenter: IRepoListPresenter {
        val userRepos = mutableListOf<GitHubRepository>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null
        override fun bindView(view: RepoItemView) {
            val repo = userRepos[view.pos]
            repo.name?.let { view.setRepoName(it) }
        }

        override fun getCount(): Int {
            return userRepos.size
        }
    }

    val repoListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
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
        val userReposRx = reposRepo.getRepositories(user)
        userReposRx
            .observeOn(scheduler)
            .doOnSubscribe { d -> compositeDisposable.addAll(d) }
            .subscribe({
                repoListPresenter.userRepos.apply {
                    clear()
                    addAll(it)
                }
                viewState.updateList()
            },
                {
                    println("onError: ${it.message}")
                })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }
}