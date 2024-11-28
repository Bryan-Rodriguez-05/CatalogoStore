package com.example.catalogostore.screens.login

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
import com.example.catalogostore.config.database.DatabaseHelper

@Composable
fun LoginSection(
    onSwitchToRegister: () -> Unit,
    onVendorLoginSuccess: () -> Unit,
    onClientLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val dbHelper = DatabaseHelper(context)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Text(text = "Iniciar Sesión", fontSize = 32.sp, modifier = Modifier.padding(bottom = 32.dp))

    OutlinedTextField(
        value = username,
        onValueChange = { username = it },
        label = { Text("Usuario") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

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

    Button(
        onClick = {
            if (username.isEmpty() || password.isEmpty()) {
                errorMessage = "Usuario y contraseña no pueden estar vacíos"
            } else if (dbHelper.checkUser(username, password)) {
                val isVendor = dbHelper.isVendor(username)
                errorMessage = ""
                Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()

                if (isVendor) {
                    // Si el usuario es un vendedor, redirige a la pantalla de agregar productos
                    onVendorLoginSuccess()
                } else {
                    // Si el usuario es un cliente, redirige a la pantalla de productos
                    onClientLoginSuccess()
                }
            } else {
                errorMessage = "Usuario o contraseña incorrectos"
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Iniciar Sesión")
    }

    if (errorMessage.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    TextButton(onClick = onSwitchToRegister) {
        Text("¿No tienes una cuenta? Regístrate")
    }
}

