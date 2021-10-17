package com.example.popularlibrariesapp.recyclerView.usersRecyclerView

interface UserItemView: IUserItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}