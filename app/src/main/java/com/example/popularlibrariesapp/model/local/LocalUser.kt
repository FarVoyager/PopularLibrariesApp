package com.example.popularlibrariesapp.model.local

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class LocalUser (
    val login: String
        ): Parcelable