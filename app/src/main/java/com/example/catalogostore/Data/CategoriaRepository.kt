package com.example.catalogostore.data

import com.example.catalogostore.model.Categoria
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.CategoriaService

interface CategoriaRepository {
    suspend fun getCategorias(): List<Categoria>
    suspend fun postCategoria(categoria: Categoria): Response
}

class DefaultCategoriaRepository(private val categoriaService: CategoriaService) :
    CategoriaRepository {
    override suspend fun getCategorias(): List<Categoria> {
        return categoriaService.getCategorias()
    }

    override suspend fun postCategoria(categoria: Categoria): Response {
        return categoriaService.postCategoria(categoria)
    }
}