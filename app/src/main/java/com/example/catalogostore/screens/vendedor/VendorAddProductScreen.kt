package com.example.catalogostore.screens.vendedor

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.catalogostore.config.database.DatabaseHelper
import androidx.compose.ui.platform.LocalContext

@Composable
fun VendorAddProductScreen(onProductAdded: () -> Unit) {
    val context = LocalContext.current
    val dbHelper = DatabaseHelper(context)

    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productStock by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productImageUri by remember { mutableStateOf<Uri?>(null) }
    var productBrand by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    val categories = dbHelper.getAllCategories()

    // Para abrir el selector de imágenes
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        productImageUri = uri
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Agregar Producto", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            // Campo para el nombre del producto
            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("Nombre del Producto") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo para el precio del producto
            OutlinedTextField(
                value = productPrice,
                onValueChange = { productPrice = it },
                label = { Text("Precio del Producto") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo para el stock del producto
            OutlinedTextField(
                value = productStock,
                onValueChange = { productStock = it },
                label = { Text("Stock del Producto") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo para la descripción del producto
            OutlinedTextField(
                value = productDescription,
                onValueChange = { productDescription = it },
                label = { Text("Descripción del Producto") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Seleccionar imagen del producto
            Button(onClick = { launcher.launch("image/*") }) {
                Text("Seleccionar Imagen del Producto")
            }
            productImageUri?.let {
                Text(text = "Imagen seleccionada: $it")
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Campo para la marca del producto
            OutlinedTextField(
                value = productBrand,
                onValueChange = { productBrand = it },
                label = { Text("Marca del Producto") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Lista desplegable para seleccionar la categoría
            var expanded by remember { mutableStateOf(false) }
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Categoría") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.second) },
                            onClick = {
                                selectedCategory = category.second
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para agregar el producto
            Button(
                onClick = {
                    val price = productPrice.toDoubleOrNull() ?: 0.0
                    val stock = productStock.toIntOrNull() ?: 0
                    val categoryId = categories.find { it.second == selectedCategory }?.first ?: -1

                    if (categoryId != -1 && productName.isNotEmpty() && price > 0 && stock >= 0 && productBrand.isNotEmpty()) {
                        // Insertar la marca y obtener el ID
                        val brandId = dbHelper.insertBrand(productBrand)

                        if (brandId != -1L) {
                            val success = dbHelper.insertProduct(
                                name = productName,
                                price = price,
                                description = productDescription,
                                stock = stock,
                                categoryId = categoryId,
                                brandId = brandId.toInt(),
                                imageUri = productImageUri.toString()
                            )

                            if (success) {
                                onProductAdded()
                            } else {
                                Log.e("VendorAddProductScreen", "Error al agregar el producto")
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Producto")
            }
        }
    }
}


