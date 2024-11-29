package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val message: String? = null,
    val status: Int? = null
)
