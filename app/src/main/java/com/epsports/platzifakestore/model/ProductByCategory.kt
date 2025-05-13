package com.epsports.platzifakestore.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductByCategory(
    @SerializedName("category")
    val category: CategoryX? = CategoryX(),
    @SerializedName("creationAt")
    val creationAt: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("images")
    val images: List<String>? = listOf(),
    @SerializedName("price")
    val price: Int? = 0,
    @SerializedName("slug")
    val slug: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("updatedAt")
    val updatedAt: String? = ""
)