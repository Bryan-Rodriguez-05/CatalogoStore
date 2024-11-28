package com.example.catalogostore.screens.client

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProductDetailsScreen(product: Product, onAddToCart: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(16.dp)
        ) {
            // Título del producto
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Descripción del producto
            Text(text = "Descripción: ${product.description}")
            Spacer(modifier = Modifier.height(8.dp))

            // Precio
            Text(text = "Precio: S/ ${product.price}")
            Spacer(modifier = Modifier.height(8.dp))

            // Stock disponible
            Text(text = "Stock disponible: ${product.stock}")
            Spacer(modifier = Modifier.height(32.dp))

            // Botón para agregar al carrito
            Button(
                onClick = onAddToCart,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar al carrito")
            }
        }
    }
}

@Preview
@Composable
fun PreviewProductDetails() {
    // Producto de ejemplo para previsualización
    val exampleProduct = Product(
        id = 1,
        name = "Producto de Ejemplo",
        price = 99.99,
        description = "Este es un producto de ejemplo.",
        stock = 10,
        image = null
    )
    ProductDetailsScreen(
        product = exampleProduct,
        onAddToCart = { /* Lógica de agregar al carrito */ }
    )
}