package com.example.catalogostore.services

import com.example.catalogostore.model.Marca
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MarcaService {
    @GET("marcas/get")
    suspend fun getMarcas(): List<Marca>

    @POST("marcas/insert")
    suspend fun postMarca(@Body request: Marca): Response
}