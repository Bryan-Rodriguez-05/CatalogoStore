package com.example.catalogostore.services

import com.example.catalogostore.model.Pedido
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PedidoService {
    @GET("pedidos/get")
    suspend fun getPedidos(): List<Pedido>

    @POST("pedidos/insert")
    suspend fun postPedido(@Body request: Pedido): Response
}