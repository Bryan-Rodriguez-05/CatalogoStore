package com.example.catalogostore.data

import com.example.catalogostore.model.DetallePedido
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.DetallePedidoService

interface DetallePedidoRepository {
    suspend fun getDetallesPedido(): List<DetallePedido>
    suspend fun postDetallePedido(detallePedido: DetallePedido): Response
}

class DefaultDetallePedidoRepository(private val detallePedidoService: DetallePedidoService) :
    DetallePedidoRepository {
    override suspend fun getDetallesPedido(): List<DetallePedido> {
        return detallePedidoService.getDetallesPedido()
    }

    override suspend fun postDetallePedido(detallePedido: DetallePedido): Response {
        return detallePedidoService.postDetallePedido(detallePedido)
    }

}