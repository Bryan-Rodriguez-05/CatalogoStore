package com.example.catalogostore.services

import com.example.catalogostore.model.Persona
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PersonaService {
    @GET("personas/get")
    suspend fun getPersonas(): List<Persona>

    @POST("personas/insert")
    suspend fun postPersona(@Body request: Persona): Response
}