package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class Entrega(
    val entrega_id: Int? = null,
    val tipo_entrega: String? = null,
    val fecha_entrega: String? = null,
    val estado_entrega: String? = null,
    val pedido: Pedido? = null
)
