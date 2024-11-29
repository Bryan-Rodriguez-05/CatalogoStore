package com.example.catalogostore.services

import com.example.catalogostore.model.Inventario
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InventarioService {

    @GET("inventarios/get")
    suspend fun getInventarios(): List<Inventario>

    @POST("inventarios/insert")
    suspend fun postInventario(@Body request: Inventario): Response
}