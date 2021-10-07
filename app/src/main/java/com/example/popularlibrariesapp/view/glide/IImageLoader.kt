package com.example.popularlibrariesapp.view.glide

interface IImageLoader<T> {
    fun loadInto(url: String, container:T)
}