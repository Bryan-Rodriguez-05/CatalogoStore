package com.example.catalogostore.data

import com.example.catalogostore.model.Pedido
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.PedidoService

interface PedidoRepository {
    suspend fun getPedidos(): List<Pedido>
    suspend fun postPedido(pedido: Pedido): Response
}

class DefaultPedidoRepository(private val pedidoService: PedidoService) :
    PedidoRepository {
    override suspend fun getPedidos(): List<Pedido> {
        return pedidoService.getPedidos()
    }
    override suspend fun postPedido(pedido: Pedido): Response {
        return pedidoService.postPedido(pedido)
    }
}