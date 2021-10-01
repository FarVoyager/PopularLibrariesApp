package com.example.popularlibrariesapp.model.network

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepo(
    @Expose val name: String? = null
): Parcelable