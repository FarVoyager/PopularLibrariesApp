package com.example.popularlibrariesapp.recyclerView

import com.example.popularlibrariesapp.recyclerView.IItemView

interface UserItemView: IItemView {
    fun setLogin(text: String)
}