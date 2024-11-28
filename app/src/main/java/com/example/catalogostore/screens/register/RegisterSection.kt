package com.example.catalogostore.screens.register

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.catalogostore.config.database.DatabaseHelper

@Composable
fun RegisterRoleSelection(onSwitchToLogin: () -> Unit) {
    var selectedRole by remember { mutableStateOf<String?>(null) }

    Text(text = "Registrar como:", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { selectedRole = "Cliente" }) {
            Text("Cliente")
        }
        Button(onClick = { selectedRole = "Vendedor" }) {
            Text("Vendedor")
        }
    }

    Spacer(modifier = Modifier.height(24.dp))

    when (selectedRole) {
        "Cliente" -> RegisterClientSection(onSwitchToLogin)
        "Vendedor" -> RegisterVendorSection(onSwitchToLogin)
    }
}

@Composable
fun RegisterClientSection(onSwitchToLogin: () -> Unit) {
    val context = LocalContext.current
    val dbHelper = DatabaseHelper(context)

    var document by remember { mutableStateOf("") }
    var documentType by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState), // Añadir el scroll aquí
        verticalArrangement = Arrangement.Top
    ) {

    Text(text = "Registrar Cliente", fontSize = 32.sp, modifier = Modifier.padding(bottom = 32.dp))

    OutlinedTextField(
        value = document,
        onValueChange = { document = it },
        label = { Text("Documento (DNI o Carné de extranjería)") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = documentType,
        onValueChange = { documentType = it },
        label = { Text("Tipo de documento") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Nombre") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = lastName,
        onValueChange = { lastName = it },
        label = { Text("Apellido") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = phone,
        onValueChange = { phone = it },
        label = { Text("Teléfono") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = address,
        onValueChange = { address = it },
        label = { Text("Dirección") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

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
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it },
        label = { Text("Confirmar Contraseña") },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(24.dp))

    Button(
        onClick = {
            if (document.isEmpty() || documentType.isEmpty() || name.isEmpty() || lastName.isEmpty() || phone.isEmpty() || address.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                errorMessage = "Todos los campos son obligatorios"
            } else if (password != confirmPassword) {
                errorMessage = "Las contraseñas no coinciden"
            } else {
                val clientInserted = dbHelper.insertClient(document, documentType, name, lastName, phone, address, username, password)
                if (clientInserted) {
                    errorMessage = ""
                    Toast.makeText(context, "Registro de cliente exitoso", Toast.LENGTH_LONG).show()
                    onSwitchToLogin()
                } else {
                    errorMessage = "Error al registrar el cliente"
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Registrar Cliente")
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
    }
}

@Composable
fun RegisterVendorSection(onSwitchToLogin: () -> Unit) {
    val context = LocalContext.current
    val dbHelper = DatabaseHelper(context)

    var document by remember { mutableStateOf("") }
    var documentType by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState), // Añadir el scroll aquí
        verticalArrangement = Arrangement.Top
    ) {


    Text(text = "Registrar Vendedor", fontSize = 32.sp, modifier = Modifier.padding(bottom = 32.dp))

    OutlinedTextField(
        value = document,
        onValueChange = { document = it },
        label = { Text("Documento (DNI o Carné de extranjería)") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = documentType,
        onValueChange = { documentType = it },
        label = { Text("Tipo de documento") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Nombre") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = lastName,
        onValueChange = { lastName = it },
        label = { Text("Apellido") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = phone,
        onValueChange = { phone = it },
        label = { Text("Teléfono") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = address,
        onValueChange = { address = it },
        label = { Text("Dirección") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

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
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it },
        label = { Text("Confirmar Contraseña") },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(24.dp))

    Button(
        onClick = {
            if (document.isEmpty() || documentType.isEmpty() || name.isEmpty() || lastName.isEmpty() || phone.isEmpty() || address.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                errorMessage = "Todos los campos son obligatorios"
            } else if (password != confirmPassword) {
                errorMessage = "Las contraseñas no coinciden"
            } else {
                val vendorInserted = dbHelper.insertVendor(document, documentType, name, lastName, phone, address, username, password)
                if (vendorInserted) {
                    errorMessage = ""
                    Toast.makeText(context, "Registro de vendedor exitoso", Toast.LENGTH_LONG).show()
                    onSwitchToLogin()
                } else {
                    errorMessage = "Error al registrar el vendedor"
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Registrar Vendedor")
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
    }
}