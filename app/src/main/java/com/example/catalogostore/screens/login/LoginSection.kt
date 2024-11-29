package com.example.catalogostore.screens.login

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.catalogostore.Data.RetrofitService
import com.example.catalogostore.Data.RetrofitServiceFactory
import com.example.catalogostore.Data.models.inputs.loginInput
import com.example.catalogostore.config.database.DatabaseHelper
import kotlinx.coroutines.launch
import kotlin.math.log

@Composable
fun LoginSection(
    onSwitchToRegister: () -> Unit,
    onVendorLoginSuccess: () -> Unit,
    onClientLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) } // Para mostrar un indicador de carga
    val coroutineScope = rememberCoroutineScope()  // Crear un scope de coroutine

    val retrofitService = RetrofitServiceFactory.makeRetrofitService()

    Text(text = "Iniciar Sesión", fontSize = 32.sp, modifier = Modifier.padding(bottom = 32.dp))

    // Campo de usuario
    OutlinedTextField(
        value = username,
        onValueChange = { username = it },
        label = { Text("Usuario") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Campo de contraseña
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Contraseña") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                val visibilityIcon = if (passwordVisible) "Ocultar" else "Mostrar"
                Text(text = visibilityIcon)
            }
        }
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Botón de login
    Button(
        onClick = {
            if (username.isEmpty() || password.isEmpty()) {
                errorMessage = "Usuario y contraseña no pueden estar vacíos"
            } else {
                isLoading = true
                // Crear el objeto con los datos para la solicitud de login
                val loginInput = loginInput(username, password)

                // Usamos el scope de la coroutine para lanzar la llamada suspendida
                coroutineScope.launch {
                    loginUserAsync(loginInput, retrofitService, context, onVendorLoginSuccess, onClientLoginSuccess)
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Iniciar Sesión")
    }

    // Mostrar mensaje de error si hay
    if (errorMessage.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

    // Mostrar un indicador de carga mientras la solicitud se procesa
    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Botón para cambiar a la pantalla de registro
    TextButton(onClick = onSwitchToRegister) {
        Text("¿No tienes una cuenta? Regístrate")
    }
}

suspend fun  loginUserAsync(
    loginInput: loginInput,
    retrofitService: RetrofitService,
    context: Context,
    onVendorLoginSuccess: () -> Unit,
    onClientLoginSuccess: () -> Unit
) {
    try {
        // Llamada al servicio Retrofit de manera asíncrona
        val response = retrofitService.LoginUser(loginInput)
        if (response.access_token.isNotEmpty()) {
            // Si la respuesta es exitosa, guardamos el token y redirigimos al usuario
           // saveToken(context, response.access_token)
            Log.d("TOKEN ACCES ",response.access_token)
            Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
            onClientLoginSuccess()
            //onVendorLoginSuccess()
            // Verificamos si el usuario es vendedor o cliente

        } else {
            Log.d("Error 1 : ", "Usuario o contraseña incorrectos")
           // errorMessage = "Usuario o contraseña incorrectos"
        }
    } catch (e: Exception) {
        Log.d("Error 2 : ", "Error en la conexión: ${e.localizedMessage}")
    }
}