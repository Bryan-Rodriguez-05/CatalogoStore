package com.example.catalogostore

import android.content.Context
import android.util.Log
import org.json.JSONArray

class CategoryManager(private val context: Context, private val dbHelper: DatabaseHelper) {

    // Leer las categorías desde un archivo JSON
    fun readCategoriesFromJson(): List<Pair<String, String>> {
        val inputStream = context.resources.openRawResource(R.raw.categoria)
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(json)

        val categories = mutableListOf<Pair<String, String>>()
        for (i in 0 until jsonArray.length()) {
            val category = jsonArray.getJSONObject(i)
            val name = category.getString("name")
            val description = category.getString("description")
            categories.add(name to description)
        }
        return categories
    }

    // Insertar categorías en la base de datos usando DatabaseHelper
    fun insertCategoriesFromJson() {
        val categories = readCategoriesFromJson()
        val allInserted = dbHelper.insertCategoriesBulk(categories)

        if (allInserted) {
            Log.d("CategoryManager", "Categorías agregadas exitosamente desde JSON")
        } else {
            Log.d("CategoryManager", "Error al agregar alguna de las categorías desde JSON")
        }
    }
}
