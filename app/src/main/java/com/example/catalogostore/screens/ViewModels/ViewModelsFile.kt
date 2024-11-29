package com.example.catalogostore.screens.ViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.catalogostore.screens.client.Product

class CartViewModel : ViewModel() {
    // Lista de productos en el carrito
    val cartItems = mutableStateListOf<Product>()

    // Función para agregar un producto al carrito
    fun addToCart(product: Product) {
        cartItems.add(product)
    }

    // Función para eliminar un producto del carrito
    fun removeFromCart(product: Product) {
        cartItems.remove(product)
    }
}
