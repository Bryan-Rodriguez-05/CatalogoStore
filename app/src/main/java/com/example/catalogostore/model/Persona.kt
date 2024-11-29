package com.example.catalogostore.model

import kotlinx.serialization.Serializable

@Serializable
data class Persona(
    val documento: Int? = null,
    val tipo_documento: String? = null,
    val nombre: String? = null,
    val apellido_paterno: String? = null,
    val apellido_materno: String? = null,
    val telefono: String? = null,
    val fecha_nacimiento: String? = null,
    val sexo: String? = null,
    val direccion: String? = null
)
