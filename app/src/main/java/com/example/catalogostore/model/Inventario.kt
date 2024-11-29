package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class Inventario(
    val inventario_id: Int? = null,
    val fecha_registro: String? = null,
    val motivo: String? = null,
    val producto: Producto? = null
)
