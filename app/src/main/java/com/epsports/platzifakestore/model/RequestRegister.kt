package com.epsports.platzifakestore.model

data class RequestRegister(
    val name: String,
    val email: String,
    val password: String,
    val avatar: String
)
