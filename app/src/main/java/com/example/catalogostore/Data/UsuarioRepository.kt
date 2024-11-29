package com.example.catalogostore.data

import com.example.catalogostore.model.Usuario
import com.example.catalogostore.model.LoginResponse
import com.example.catalogostore.services.UsuarioService

interface UsuarioRepository {
    suspend fun postLogin(usuario: Usuario): LoginResponse
}

class DefaultUsuarioRepository(private val usuarioService: UsuarioService) :
    UsuarioRepository {
    override suspend fun postLogin(usuario: Usuario): LoginResponse {
        return usuarioService.postLogin(usuario)
    }
}