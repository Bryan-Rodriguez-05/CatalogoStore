package com.example.catalogostore.screens.client

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catalogostore.config.database.DatabaseHelper
import com.example.catalogostore.screens.ViewModels.CartViewModel
import com.example.catalogostore.screens.ViewModels.OrderViewModel
import com.example.catalogostore.screens.ViewModels.OrderViewModelFactory


@Composable
fun CartScreen(
    cartViewModel: CartViewModel = viewModel(),
    orderViewModel: OrderViewModel = viewModel(factory = OrderViewModelFactory(DatabaseHelper(LocalContext.current))), // Proveer el factory con el DatabaseHelper
    onCheckout: () -> Unit
) {
    // Instancia de DatabaseHelper
    val dbHelper = DatabaseHelper(LocalContext.current)

    // Usar el ViewModelProvider.Factory para crear el OrderViewModel
    val orderViewModel: OrderViewModel = viewModel(
        factory = OrderViewModelFactory(dbHelper)
    )
    val cartItems by remember { derivedStateOf { cartViewModel.cartItems } }
    val total = cartItems.sumOf { it.price }
    Log.d("cartscreen", "Carrito items: ${cartItems.size}") // Verifica los items del carrito

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Tu Carrito", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            if (cartItems.isEmpty()) {
                Text("Tu carrito está vacío")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(cartItems) { product ->
                        CartItem(
                            product = product,
                            onRemoveFromCart = { cartViewModel.removeFromCart(product) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        // Crear el pedido al realizar el checkout
                        orderViewModel.createOrder(clientId = 1, cartItems = cartItems, total = total)  // Supongamos que el clientId es 1
                        onCheckout()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Proceder a la compra")
                }
            }
        }
    }
}



@Composable
fun CartItem(
    product: Product,
    onRemoveFromCart: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(product.name)
        Text("S/ ${product.price}")
        IconButton(onClick = onRemoveFromCart) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
        }
    }
}
