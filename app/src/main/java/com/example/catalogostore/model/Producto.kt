package com.example.catalogostore.model

import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class Producto(
    val sku: Int? = null,
    val nombre: String? = null,
    val precio: BigDecimal? = null,
    val imagen: List<ByteArray>? = null,
    val descripcion: String? = null,
    val unidades: Int? = null,
    val marca: Marca? = null,
    val categoria: Categoria? = null,
    val vendedor: Vendedor? = null

)
