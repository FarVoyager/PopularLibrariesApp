package com.example.popularlibrariesapp.recyclerView.usersRecyclerView

interface IListPresenter<V: IUserItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter: IListPresenter<UserItemView>