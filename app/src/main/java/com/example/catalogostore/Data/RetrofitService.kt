package com.example.catalogostore.Data

import com.example.catalogostore.Data.models.inputs.loginInput
import com.example.catalogostore.Data.models.responses.loginResponseOk
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Body

interface RetrofitService {

    @POST("usuarios/login")
    suspend fun LoginUser(
        @Body loginInput: loginInput
    ) : loginResponseOk
}
object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://dsm-g02-catalogstore-backend.onrender.com/")
                .addConverterFactory (GsonConverterFactory.create())
            .build().create (RetrofitService::class.java)
    }
}