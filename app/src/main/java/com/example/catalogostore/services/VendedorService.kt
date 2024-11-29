package com.example.catalogostore.services

import com.example.catalogostore.model.Vendedor
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface VendedorService {
    @GET("vendedores/get")
    suspend fun getVendedores(): List<Vendedor>

    @POST("vendedores/insert")
    suspend fun postVendedor(@Body request: Vendedor): Response
}