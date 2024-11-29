package com.example.catalogostore.services

import com.example.catalogostore.model.Entrega
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EntregaService {

    @GET("entregas/get")
    suspend fun getEntregas(): List<Entrega>

    @POST("entregas/insert")
    suspend fun postEntrega(@Body request: Entrega): Response
}