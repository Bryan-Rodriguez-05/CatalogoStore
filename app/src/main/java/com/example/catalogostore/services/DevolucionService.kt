package com.example.catalogostore.services

import com.example.catalogostore.model.Devolucion
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DevolucionService {
    @GET("devoluciones/get")
    suspend fun getDevolucionPedido(): List<Devolucion>

    @POST("devoluciones/insert")
    suspend fun postDevolucionPedido(@Body request: Devolucion): Response
}