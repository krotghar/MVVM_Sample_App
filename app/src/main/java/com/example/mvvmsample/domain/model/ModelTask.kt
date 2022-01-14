package com.example.mvvmsample.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelTask(
    val id: Int,
    val taskName: String,
    val taskDescription: String
) : Parcelable