package com.example.catalogostore.data

import com.example.catalogostore.model.Persona
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.PersonaService

interface PersonaRepository {
    suspend fun getPersonas(): List<Persona>
    suspend fun postPersona(persona: Persona): Response
}

class DefaultPersonaRepository(private val personaService: PersonaService) :
    PersonaRepository {
    override suspend fun getPersonas(): List<Persona> {
        return personaService.getPersonas()
    }
    override suspend fun postPersona(persona: Persona): Response {
        return personaService.postPersona(persona)
    }
}