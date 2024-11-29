package com.example.catalogostore.services

import com.example.catalogostore.model.DetallePedido
import com.example.catalogostore.model.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface DetallePedidoService {
    @GET("detalles_pedido/get")
    suspend fun getDetallesPedido(): List<DetallePedido>

    @POST("detalles_pedido/insert")
    suspend fun postDetallePedido(@Body request: DetallePedido): Response
}