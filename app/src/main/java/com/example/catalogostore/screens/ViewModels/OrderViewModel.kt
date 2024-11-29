package com.example.catalogostore.screens.ViewModels

import androidx.lifecycle.ViewModel
import com.example.catalogostore.config.database.DatabaseHelper
import com.example.catalogostore.screens.client.Product

class OrderViewModel(private val dbHelper: DatabaseHelper) : ViewModel() {

    // Función para crear un pedido
    fun createOrder(clientId: Int, cartItems: List<Product>, total: Double) {
        // Insertar un comprobante de factura (puedes definir un método para crear una factura si es necesario)
        val comprobanteId = 1  // Definir la ID de comprobante, esto depende de tu lógica
        val subtotal = cartItems.sumOf { it.price }  // Sumar el precio de todos los productos
        val igv = subtotal * 0.18  // Ejemplo de IGV (impuesto)

        // Insertar el pedido
        val orderId = dbHelper.insertOrder(clientId, subtotal, igv, total, comprobanteId)

        // Insertar los detalles de cada producto del carrito
        cartItems.forEach { product ->
            val totalProducto = product.price * product.stock // Total por producto, puedes ajustar esto según tu lógica
            dbHelper.insertOrderDetails(orderId, product.id, product.price, 1, 0.0, totalProducto)
            dbHelper.updateProductStock(product.id, product.stock - 1) // Actualizar el stock del producto
        }
    }
}

