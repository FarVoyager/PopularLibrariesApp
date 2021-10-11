package com.example.popularlibrariesapp.recyclerView.reposRecyclerView

interface IListPresenter<V: IRepoItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IRepoListPresenter: IListPresenter<RepoItemView>