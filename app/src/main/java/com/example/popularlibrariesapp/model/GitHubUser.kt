package com.example.popularlibrariesapp.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class GitHubUser (
    val login: String
        ): Parcelable