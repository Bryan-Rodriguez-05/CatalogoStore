package com.example.catalogostore.data

import com.example.catalogostore.model.Cliente
import com.example.catalogostore.model.Response
import com.example.catalogostore.services.ClienteService

interface ClienteRepository {
    suspend fun getClientes(): List<Cliente>
    suspend fun postCliente(cliente: Cliente): Response
}

class DefaultClienteRepository(private val clienteService: ClienteService) :
    ClienteRepository {
    override suspend fun getClientes(): List<Cliente> {
        return clienteService.getClientes()
    }
    override suspend fun postCliente(cliente: Cliente): Response {
        return clienteService.postCliente(cliente)
    }
}