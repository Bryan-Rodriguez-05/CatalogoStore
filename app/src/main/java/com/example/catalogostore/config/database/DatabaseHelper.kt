package com.example.catalogostore.config.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createPersonTable = ("CREATE TABLE " + TABLE_PERSON + " ("
                + COLUMN_DOCUMENT + " TEXT PRIMARY KEY, "
                + COLUMN_DOCUMENT_TYPE + " TEXT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_LAST_NAME + " TEXT, "
                + COLUMN_PHONE + " TEXT, "
                + COLUMN_ADDRESS + " TEXT)")

        val createUserTable = ("CREATE TABLE " + TABLE_USER + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_DOCUMENT + " TEXT, "
                + "FOREIGN KEY(" + COLUMN_DOCUMENT + ") REFERENCES " + TABLE_PERSON + "(" + COLUMN_DOCUMENT + "))")

        val createClientTable = ("CREATE TABLE " + TABLE_CLIENT + " ("
                + COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_ID + " INTEGER, "
                + "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + "))")

        val createVendorTable = ("CREATE TABLE " + TABLE_VENDOR + " ("
                + COLUMN_VENDOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_ID + " INTEGER, "
                + "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + "))")

        val createCategoryTable = ("CREATE TABLE " + TABLE_CATEGORY + " ("
                + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_DESCRIPTION + " TEXT)")

        val createProductTable = ("CREATE TABLE " + TABLE_PRODUCT + " ("
                + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PRODUCT_NAME + " TEXT, "
                + COLUMN_PRODUCT_PRICE + " DOUBLE, "
                + COLUMN_PRODUCT_IMAGE + " TEXT, "
                + COLUMN_PRODUCT_DESCRIPTION + " TEXT, "
                + COLUMN_PRODUCT_STOCK + " INTEGER, "
                + COLUMN_FOREIGN_CATEGORY_ID + " INTEGER, "
                + COLUMN_FOREIGN_BRAND_ID + " INTEGER, "
                + "FOREIGN KEY(" + COLUMN_FOREIGN_CATEGORY_ID + ") REFERENCES " + TABLE_CATEGORY + "(" + COLUMN_CATEGORY_ID + "), "
                + "FOREIGN KEY(" + COLUMN_FOREIGN_BRAND_ID + ") REFERENCES " + TABLE_BRAND + "(" + COLUMN_BRAND_ID + "))")

        val createInventoryTable = ("CREATE TABLE " + TABLE_INVENTORY + " ("
                + COLUMN_INVENTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_INVENTORY_STOCK + " INTEGER, "
                + COLUMN_INVENTORY_DATE + " DATE, "
                + COLUMN_INVENTORY_REASON + " TEXT, "
                + COLUMN_FOREIGN_PRODUCT_ID + " INTEGER, "
                + "FOREIGN KEY(" + COLUMN_FOREIGN_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCT + "(" + COLUMN_PRODUCT_ID + "))")

        val createBrandTable = ("CREATE TABLE " + TABLE_BRAND + " ("
                + COLUMN_BRAND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BRAND_DESCRIPTION + " TEXT)")

        db.execSQL(createPersonTable)
        db.execSQL(createUserTable)
        db.execSQL(createClientTable)
        db.execSQL(createVendorTable)
        db.execSQL(createCategoryTable)
        db.execSQL(createProductTable)
        db.execSQL(createInventoryTable)
        db.execSQL(createBrandTable)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENDOR)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BRAND)
        onCreate(db)
    }

    fun insertPerson(document: String, documentType: String, name: String, lastName: String, phone: String, address: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_DOCUMENT, document)
            put(COLUMN_DOCUMENT_TYPE, documentType)
            put(COLUMN_NAME, name)
            put(COLUMN_LAST_NAME, lastName)
            put(COLUMN_PHONE, phone)
            put(COLUMN_ADDRESS, address)
        }
        val result = db.insert(TABLE_PERSON, null, contentValues)
        return result != -1L
    }

    fun insertUser(username: String, password: String, document: String): Long {
        val db = this.writableDatabase
        val trimmedUsername = username.trim()
        val trimmedPassword = password.trim()

        val contentValues = ContentValues().apply {
            put(COLUMN_USERNAME, trimmedUsername)
            put(COLUMN_PASSWORD, trimmedPassword)
            put(COLUMN_DOCUMENT, document)
        }
        return db.insert(TABLE_USER, null, contentValues)
    }

    fun insertClient(document: String, documentType: String, name: String, lastName: String, phone: String, address: String, username: String, password: String): Boolean {
        val personInserted = insertPerson(document, documentType, name, lastName, phone, address)
        if (!personInserted) {
            return false
        }

        val userId = insertUser(username, password, document)
        if (userId == -1L) {
            return false
        }

        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_USER_ID, userId)
        }
        val result = db.insert(TABLE_CLIENT, null, contentValues)
        return result != -1L
    }

    fun insertVendor(document: String, documentType: String, name: String, lastName: String, phone: String, address: String, username: String, password: String): Boolean {
        val personInserted = insertPerson(document, documentType, name, lastName, phone, address)
        if (!personInserted) {
            return false
        }

        val userId = insertUser(username, password, document)
        if (userId == -1L) {
            return false
        }

        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_USER_ID, userId)
        }
        val result = db.insert(TABLE_VENDOR, null, contentValues)
        return result != -1L
    }

    fun insertCategoriesBulk(categories: List<Pair<String, String>>): Boolean {
        val db = this.writableDatabase
        try {
            db.beginTransaction()
            for (category in categories) {
                val contentValues = ContentValues().apply {
                    put(COLUMN_NAME, category.first.trim())
                    put(COLUMN_DESCRIPTION, category.second.trim())
                }
                val result = db.insert(TABLE_CATEGORY, null, contentValues)
                if (result == -1L) {
                    return false // Si falla algún insert, se sale de la función con `false`
                }
            }
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        } finally {
            db.endTransaction()
        }
        db.close()
        return true
    }


    fun checkUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USER WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    // Método para obtener todas las categorías
    fun getAllCategories(): List<Pair<Int, String>> {
        val db = this.readableDatabase
        val categories = mutableListOf<Pair<Int, String>>()
        val cursor = db.rawQuery("SELECT $COLUMN_CATEGORY_ID, $COLUMN_NAME FROM $TABLE_CATEGORY", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                categories.add(Pair(id, name))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categories
    }

    // Método para insertar una nueva marca y obtener el ID generado
    fun insertBrand(brandDescription: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_BRAND_DESCRIPTION, brandDescription.trim())
        }
        return db.insert(TABLE_BRAND, null, contentValues)
    }

    // Método para insertar un producto
    fun insertProduct(name: String, price: Double, description: String, stock: Int, categoryId: Int, brandId: Int, imageUri: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_PRODUCT_NAME, name)
            put(COLUMN_PRODUCT_PRICE, price)
            put(COLUMN_PRODUCT_DESCRIPTION, description)
            put(COLUMN_PRODUCT_STOCK, stock)
            put(COLUMN_FOREIGN_CATEGORY_ID, categoryId)
            put(COLUMN_FOREIGN_BRAND_ID, brandId)
            put(COLUMN_PRODUCT_IMAGE, imageUri)
        }
        val result = db.insert(TABLE_PRODUCT, null, contentValues)
        return result != -1L
    }

    fun isVendor(username: String): Boolean {
        val db = this.readableDatabase
        val query = """
        SELECT * FROM $TABLE_USER u
        INNER JOIN $TABLE_VENDOR v ON u.$COLUMN_USER_ID = v.$COLUMN_USER_ID
        WHERE u.$COLUMN_USERNAME = ?
    """
        val cursor = db.rawQuery(query, arrayOf(username))
        val isVendor = cursor.count > 0
        cursor.close()
        return isVendor
    }


    companion object {
        private const val DATABASE_VERSION = 4
        private const val DATABASE_NAME = "AppStoreDB.db"

        private const val TABLE_PERSON = "person"
        private const val COLUMN_DOCUMENT = "document"
        private const val COLUMN_DOCUMENT_TYPE = "document_type"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_LAST_NAME = "last_name"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_ADDRESS = "address"

        private const val TABLE_USER = "user"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        private const val TABLE_CLIENT = "client"
        private const val COLUMN_CLIENT_ID = "client_id"


        private const val TABLE_VENDOR = "vendor"
        private const val COLUMN_VENDOR_ID = "vendor_id"

        private const val TABLE_CATEGORY = "category"
        private const val COLUMN_CATEGORY_ID = "category_id"
        private const val COLUMN_DESCRIPTION = "description"

        // Definiciones de la tabla Producto
        private const val TABLE_PRODUCT = "product"
        private const val COLUMN_PRODUCT_ID = "product_id"
        private const val COLUMN_PRODUCT_NAME = "name"
        private const val COLUMN_PRODUCT_PRICE = "price"
        private const val COLUMN_PRODUCT_IMAGE = "image"
        private const val COLUMN_PRODUCT_DESCRIPTION = "description"
        private const val COLUMN_PRODUCT_STOCK = "stock"
        private const val COLUMN_FOREIGN_BRAND_ID = "brand_id"
        private const val COLUMN_FOREIGN_CATEGORY_ID = "categoryId"

        // Definiciones de la tabla Inventario
        private const val TABLE_INVENTORY = "inventory"
        private const val COLUMN_INVENTORY_ID = "inventory_id"
        private const val COLUMN_INVENTORY_STOCK = "stock"
        private const val COLUMN_INVENTORY_DATE = "fecha_actualizacion"
        private const val COLUMN_INVENTORY_REASON = "motivo"
        private const val COLUMN_FOREIGN_PRODUCT_ID = "product_id"

        // Definiciones de la tabla Marca
        private const val TABLE_BRAND = "brand"
        private const val COLUMN_BRAND_ID = "marca_id"
        private const val COLUMN_BRAND_DESCRIPTION = "descripcion"

    }
}