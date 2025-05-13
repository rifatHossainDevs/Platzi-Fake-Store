package com.epsports.platzifakestore.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductDetails(
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
){
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
}