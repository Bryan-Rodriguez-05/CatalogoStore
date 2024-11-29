package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class Usuario(
    val correo: String? = null,
    val password: String? = null
)
