package com.rylov.tipseveryday.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: Int,
    val day: Int,
    val title: String,
    val shortDescription: String,
    val fullDescription: String,
    val imageId: Int
) : Parcelable