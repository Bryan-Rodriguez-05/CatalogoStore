package com.example.catalogostore.data

import com.example.catalogostore.model.Marca
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.MarcaService

interface MarcaRepository {
    suspend fun getMarcas(): List<Marca>
    suspend fun postMarca(marca: Marca): Response
}

class DefaultMarcaRepository(private val marcaService: MarcaService) :
    MarcaRepository {
    override suspend fun getMarcas(): List<Marca> {
        return marcaService.getMarcas()
    }
    override suspend fun postMarca(marca: Marca): Response {
        return marcaService.postMarca(marca)
    }
}