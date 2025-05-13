package com.epsports.platzifakestore.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CategoryX(
    @SerializedName("creationAt")
    val creationAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)