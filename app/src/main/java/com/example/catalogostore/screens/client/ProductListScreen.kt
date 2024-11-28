package com.example.catalogostore.screens.client
import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.example.catalogostore.config.database.DatabaseHelper
import com.example.catalogostore.R

@Composable
fun ProductListScreen(onLogout: () -> Unit) {
    val context = LocalContext.current
    val dbHelper = DatabaseHelper(context)
    val products = dbHelper.getAllProducts()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Button(onClick = onLogout, modifier = Modifier.padding(16.dp)) {
                Text(text = "Cerrar sesión")
            }
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products) { product ->
                    ProductItem(product = product, context = context)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, context: Context) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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



// Clase para representar un producto
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val stock: Int,
    val image: ByteArray?
)

// Método para obtener todos los productos
fun DatabaseHelper.getAllProducts(): List<Product> {
    val db = this.readableDatabase
    val products = mutableListOf<Product>()
    val cursor = db.rawQuery("SELECT * FROM product", null)
    if (cursor.moveToFirst()) {
        do {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("product_id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
            val description = cursor.getString(cursor.getColumnIndexOrThrow("description"))
            val stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"))
            val imageBlob = cursor.getBlob(cursor.getColumnIndexOrThrow("image"))
            products.add(Product(id, name, price, description, stock, imageBlob))
        } while (cursor.moveToNext())
    }
    cursor.close()
    return products
}

