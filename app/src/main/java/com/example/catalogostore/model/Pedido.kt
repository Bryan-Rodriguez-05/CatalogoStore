package com.example.catalogostore.model

import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class Pedido(
    val pedido_id: Int? = null,
    val fecha_pedido: String? = null,
    val estado_pedido: String? = null,
    val igv: BigDecimal? = null,
    val subtotal: BigDecimal? = null,
    val monto_total: BigDecimal? = null,
    val cliente: Cliente? = null,
    val comprobante: TipoComprobante? = null
)
