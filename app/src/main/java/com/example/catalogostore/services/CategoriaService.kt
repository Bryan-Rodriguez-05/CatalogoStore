package com.example.catalogostore.services

import com.example.catalogostore.model.Categoria
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CategoriaService {
    @GET("categorias/get")
    suspend fun getCategorias(): List<Categoria>

    @POST("categorias/insert")
    suspend fun postCategoria(@Body  request: Categoria): Response
}