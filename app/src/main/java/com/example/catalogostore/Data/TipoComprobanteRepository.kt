package com.example.catalogostore.data

import com.example.catalogostore.model.TipoComprobante
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.TipoComprobanteService

interface TipoComprobanteRepository {
    suspend fun getTiposComprobante(): List<TipoComprobante>
    suspend fun postTipoComprobante(tipoComprobante: TipoComprobante): Response
}

class DefaultTipoComprobanteRepository(private val tipoComprobanteService: TipoComprobanteService) :
    TipoComprobanteRepository {
    override suspend fun getTiposComprobante(): List<TipoComprobante> {
        return tipoComprobanteService.getTiposComprobante()
    }
    override suspend fun postTipoComprobante(cliente: TipoComprobante): Response {
        return tipoComprobanteService.postTipoComprobante(cliente)
    }
}