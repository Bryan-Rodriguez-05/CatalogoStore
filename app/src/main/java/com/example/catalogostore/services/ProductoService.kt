package com.example.catalogostore.services

import com.example.catalogostore.model.Producto
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductoService {
    @GET("productos/get")
    suspend fun getProductos(): List<Producto>

    @POST("productos/insert")
    suspend fun postProducto(@Body request: Producto): Response
}