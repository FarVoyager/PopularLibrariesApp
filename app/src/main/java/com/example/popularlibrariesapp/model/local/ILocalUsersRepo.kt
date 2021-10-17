package com.example.popularlibrariesapp.model.local

import io.reactivex.rxjava3.core.Single

interface ILocalUsersRepo {
    fun getUsers(): Single<List<LocalUser>>
}