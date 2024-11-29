package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class TipoComprobante(
    val comprobante_id: Int? = null,
    val descripcion_comprobante: String? = null,
    val serie: String? = null,
    val correlativo: String? = null,
)
