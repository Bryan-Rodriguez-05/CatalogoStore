package com.example.catalogostore.screens.vendedor

// VendorMenuScreen.kt
import androidx.compose.compiler.plugins.kotlin.ComposeCallableIds.remember
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames.remember
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VendorMenuScreen(onAddProductClick: () -> Unit, onLogout: () -> Unit) {
    var showProductScreen by remember { mutableStateOf(false) }

    if (showProductScreen) {
        VendorAddProductScreen(onProductAdded = { showProductScreen = false }, onLogout = onLogout)
    } else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Menú del Vendedor", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { showProductScreen = true },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                ) {
                    Text("Agregar Producto")
                }

                Button(
                    onClick = onLogout,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                ) {
                    Text("Cerrar sesión")
                }
            }
        }
    }
}


