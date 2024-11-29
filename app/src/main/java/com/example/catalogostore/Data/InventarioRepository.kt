package com.example.catalogostore.data

import com.example.catalogostore.model.Inventario
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.InventarioService

interface InventarioRepository {
    suspend fun getInventarios(): List<Inventario>
    suspend fun postInventario(cliente: Inventario): Response
}

class DefaultInventarioRepository(private val inventarioService: InventarioService) :
    InventarioRepository {
    override suspend fun getInventarios(): List<Inventario> {
        return inventarioService.getInventarios()
    }
    override suspend fun postInventario(inventario: Inventario): Response {
        return inventarioService.postInventario(inventario)
    }
}