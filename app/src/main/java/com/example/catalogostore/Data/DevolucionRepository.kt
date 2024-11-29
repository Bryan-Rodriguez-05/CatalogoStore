package com.example.catalogostore.data

import com.example.catalogostore.model.Devolucion
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.DevolucionService

interface DevolucionRepository{
    suspend fun getDevolucionPedido(): List<Devolucion>
    suspend fun postDevolucionPedido(devolucion: Devolucion): Response
}

class DefaultDevolucionRepository(private val devolucionPedidoService: DevolucionService) :
    DevolucionRepository {
    override suspend fun getDevolucionPedido(): List<Devolucion> {
        return devolucionPedidoService.getDevolucionPedido()
    }
    override suspend fun postDevolucionPedido(devolucion: Devolucion): Response {
        return devolucionPedidoService.postDevolucionPedido(devolucion)
    }
}