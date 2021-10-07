package com.example.popularlibrariesapp.model.local

import io.reactivex.rxjava3.core.Single

class LocalUsersRepo : ILocalUsersRepo {

    private val repositories = listOf(
            LocalUser("DarthVaderShinobi"),
            LocalUser("MiwkaUbivawka"),
            LocalUser("FastAsHeckBoi"),
            LocalUser("Onotole1"),
            LocalUser("LexaPetrov666")
    )

    override fun getUsers(): Single<List<LocalUser>> {
        return Single.just(repositories)
    }
}