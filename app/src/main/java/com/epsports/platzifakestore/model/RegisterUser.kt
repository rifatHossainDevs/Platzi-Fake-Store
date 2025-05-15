package com.epsports.platzifakestore.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RegisterUser(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("creationAt")
    val creationAt: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("role")
    val role: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)