package com.example.catalogostore.data

import com.example.catalogostore.model.Producto
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.ProductoService

interface ProductoRepository {
    suspend fun getProductos(): List<Producto>
    suspend fun postProducto(producto: Producto): Response
}

class DefaultProductoRepository(private val productoService: ProductoService) :
    ProductoRepository {
    override suspend fun getProductos(): List<Producto> {
        return productoService.getProductos()
    }
    override suspend fun postProducto(producto: Producto): Response {
        return productoService.postProducto(producto)
    }
}