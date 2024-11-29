package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class Vendedor(
    val vendedor_id: Int? = null,
    val usuario: Usuario? = null
)
