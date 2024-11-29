package com.example.catalogostore.services

import com.example.catalogostore.model.Usuario
import com.example.catalogostore.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioService {
    @POST("/usuarios/login")
    suspend fun postLogin(@Body request: Usuario): LoginResponse
}