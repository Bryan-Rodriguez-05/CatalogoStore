package com.example.catalogostore.services

import com.example.catalogostore.model.TipoComprobante
import com.example.catalogostore.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TipoComprobanteService {
    @GET("tipos_comprobante/get")
    suspend fun getTiposComprobante(): List<TipoComprobante>

    @POST("tipos_comprobante/insert")
    suspend fun postTipoComprobante(@Body request: TipoComprobante): Response
}