package com.example.catalogostore.data

import com.example.catalogostore.model.Vendedor
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.VendedorService

interface VendedorRepository {
    suspend fun getVendedores(): List<Vendedor>
    suspend fun postVendedor(cliente: Vendedor): Response
}

class DefaultVendedorRepository(private val vendedorService: VendedorService) :
    VendedorRepository {
    override suspend fun getVendedores(): List<Vendedor> {
        return vendedorService.getVendedores()
    }
    override suspend fun postVendedor(cliente: Vendedor): Response {
        return vendedorService.postVendedor(cliente)
    }
}