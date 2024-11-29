package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class Categoria(
    val categoria_id: Int? = null,
    val nombre: String? = null,
    val descripcion: String? = null
)
@Serializable
data class CategoriaRequest(
    val nombre: String? = null,
    val descripcion: String? = null
)
