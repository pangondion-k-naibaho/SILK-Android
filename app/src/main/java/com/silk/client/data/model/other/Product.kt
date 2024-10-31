package com.silk.client.data.model.other

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val name: String,
    val price: String,
    val stockStatus: String,
    val rating: Int,
    val image: Int
):Parcelable