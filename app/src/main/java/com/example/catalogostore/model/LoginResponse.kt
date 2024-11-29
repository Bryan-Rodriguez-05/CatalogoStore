package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val message: String,
    val access_token: String?,
    val refresh_token: String?,
    val status: Int
)
