package com.epsports.platzifakestore.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Category(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("creationAt")
    val creationAt: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null,
    @SerializedName("slug")
    val slug: String? = null
)
