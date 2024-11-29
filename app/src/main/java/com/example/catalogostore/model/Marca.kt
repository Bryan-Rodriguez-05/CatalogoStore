package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class Marca(
    val marca_id: Int? = null,
    val descripcion: String? = null
)
