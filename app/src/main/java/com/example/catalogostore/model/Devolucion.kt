package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class Devolucion(
    val devolucion_id: Int? = null,
    val cantidad: Int? = null,
    val motivo: String? = null,
    val fecha_devolucion: String? = null,
    val Producto: Producto? = null,
    val Pedido: Pedido? = null
)
