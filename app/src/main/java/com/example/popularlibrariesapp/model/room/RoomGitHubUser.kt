package com.example.popularlibrariesapp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGitHubUser(
    @PrimaryKey var id: String,
    var login: String,
    var avatarUrl: String,
    var reposUrl:String
)