package com.example.catalogostore.screens.client
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.catalogostore.config.database.DatabaseHelper
import com.example.catalogostore.R
import com.example.catalogostore.screens.ViewModels.CartViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    onClickCard: () -> Unit,
    onLogout: () -> Unit,
    cartViewModel: CartViewModel = viewModel() // Obtener el ViewModel
) {
    val context = LocalContext.current
    val dbHelper = DatabaseHelper(context)
    val products = dbHelper.getAllProducts().filter { it.stock > 0 }

    // Estado para controlar la visibilidad del diálogo
    var showProductDetailsDialog by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TopAppBar(
                title = { Text("Productos") },
                actions = {
                    // Icono del carrito
                    IconButton(onClick = {
                        Log.d("Click,,, :v", "onclickcard ....")
                        onClickCard()// Aquí cambias el estado
                        Log.d("showCardMenu", "showCardMenu =OK")  // Log para verificar
                    }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito")
                    }
                }
            )

            // Botón para cerrar sesión
            Button(onClick = onLogout, modifier = Modifier.padding(16.dp)) {
                Text(text = "Cerrar sesión")
            }

            // Lista de productos
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products) { product ->
                    ProductItem(
                        product = product,
                        context = context,
                        onProductSelected = {
                            selectedProduct = it
                            showProductDetailsDialog = true
                        },
                        onAddToCart = {
                            // Agregar al carrito
                            cartViewModel.addToCart(product)
                        }
                    )
                }
            }

            // Mostrar el diálogo de detalles del producto si se ha seleccionado un producto
            selectedProduct?.let { product ->
                if (showProductDetailsDialog) {
                    ProductDetailsDialog(
                        product = product,
                        onDismiss = { showProductDetailsDialog = false },
                        onAddToCart = {
                            cartViewModel.addToCart(product)
                            showProductDetailsDialog = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, context: Context, onProductSelected: (Product) -> Unit,onAddToCart: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductSelected(product) }, // Al hacer clic, selecciona el producto
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Mostrar la imagen del producto si está disponible
            product.image?.let {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = product.name,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                )
            } ?: Image(
                painter = painterResource(id = R.drawable.placeholder), // Imagen de marcador de posición
                contentDescription = "Placeholder",
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Precio: S/ ${product.price}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = product.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun ProductDetailsDialog(
    product: Product,
    onDismiss: () -> Unit,
    onAddToCart: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Detalles del Producto") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Nombre: ${product.name}")
                Text("Precio: S/ ${product.price}")
                Text("Descripción: ${product.description}")
            }
        },
        confirmButton = {
            Button(onClick = onAddToCart) {
                Text("Agregar al carrito")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}


// Clase para representar un producto
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val stock: Int,
    val image: ByteArray?
)



