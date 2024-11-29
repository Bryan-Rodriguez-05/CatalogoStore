package com.example.catalogostore.data

import com.example.catalogostore.model.Entrega
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.EntregaService

interface EntregaRepository {
    suspend fun getEntregas(): List<Entrega>
    suspend fun postEntrega(entrega: Entrega): Response
}

class DefaultEntregaRepository(private val entregaService: EntregaService) :
    EntregaRepository {
    override suspend fun getEntregas(): List<Entrega> {
        return entregaService.getEntregas()
    }
    override suspend fun postEntrega(entrega: Entrega): Response {
        return entregaService.postEntrega(entrega)
    }
}