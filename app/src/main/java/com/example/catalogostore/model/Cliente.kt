package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class Cliente(
    val cliente_id: Int? = null,
    val preferencias: String? = null,
    val usuario: Usuario? = null
)
