package com.example.catalogostore.model

import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class DetallePedido(
    val detalle_id: Int? = null,
    val unidades: Int? = null,
    val costo_unidad: BigDecimal? = null,
    val descuento: BigDecimal? = null,
    val total: BigDecimal? = null,
    val pedido: Pedido? = null,
    val producto: Producto? = null
)
