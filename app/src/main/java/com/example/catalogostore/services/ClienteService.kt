package com.example.catalogostore.services

import com.example.catalogostore.model.Cliente
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClienteService {
    @GET("clientes/get")
    suspend fun getClientes(): List<Cliente>

    @POST("clientes/insert")
    suspend fun postCliente(@Body request: Cliente): Response
}