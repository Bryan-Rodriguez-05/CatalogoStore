package com.example.catalogostore.Data.models.responses

data class loginResponseOk(
    val access_token: String,
    val message: String,
    val refresh_token: String
)