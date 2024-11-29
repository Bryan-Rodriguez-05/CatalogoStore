package com.example.catalogostore.config.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.catalogostore.screens.client.Product
import java.time.LocalDate


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
                + COLUMN_PRODUCT_IMAGE + " BLOB, "
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

        val createOrderDetailTable = ("CREATE TABLE " + TABLE_ORDER_DETAIL + " (" +
                COLUMN_ORDER_DETAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ORDER_DETAIL_UNITS + " INTEGER NOT NULL, " +
                COLUMN_ORDER_DETAIL_UNIT_COST + " DOUBLE NOT NULL, " +
                COLUMN_ORDER_DETAIL_DISCOUNT + " DOUBLE NOT NULL, " +
                COLUMN_ORDER_DETAIL_TOTAL + " DOUBLE NOT NULL, " +
                COLUMN_FOREIGN_DETAIL_ORDER_ID + " INTEGER NOT NULL, " +
                COLUMN_FOREIGN_DETAIL_PRODUCT_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + COLUMN_FOREIGN_DETAIL_ORDER_ID + ") REFERENCES " + TABLE_ORDERS + "(" + COLUMN_ORDERS_ID + "), " +
                "FOREIGN KEY(" + COLUMN_FOREIGN_DETAIL_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCT + "(" + COLUMN_PRODUCT_ID + "))")

        val createReturnTable = ("CREATE TABLE " + TABLE_RETURN + " (" +
                COLUMN_RETURN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RETURN_QUANTITY + " INTEGER NOT NULL, " +
                COLUMN_RETURN_REASON + " TEXT NOT NULL, " +
                COLUMN_RETURN_DATE + " DATE NOT NULL, " +
                COLUMN_FOREIGN_RETURN_PRODUCT_ID + " INTEGER NOT NULL, " +
                COLUMN_FOREIGN_RETURN_ORDER_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + COLUMN_FOREIGN_RETURN_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCT + "(" + COLUMN_PRODUCT_ID + "), " +
                "FOREIGN KEY(" + COLUMN_FOREIGN_RETURN_ORDER_ID + ") REFERENCES " + TABLE_ORDERS + "(" + COLUMN_ORDERS_ID + "))")

        val createDeliveryTable = ("CREATE TABLE " + TABLE_DELIVERY + " (" +
                COLUMN_DELIVERY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DELIVERY_TYPE + " TEXT NOT NULL, " +
                COLUMN_DELIVERY_DATE + " DATE NOT NULL, " +
                COLUMN_DELIVERY_STATUS + " TEXT NOT NULL, " +
                COLUMN_FOREIGN_DELIVERY_ORDER_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + COLUMN_FOREIGN_DELIVERY_ORDER_ID + ") REFERENCES " + TABLE_ORDERS + "(" + COLUMN_ORDERS_ID + "))")

        val createOrdersTable = ("CREATE TABLE " + TABLE_ORDERS + " (" +
                COLUMN_ORDERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ORDERS_DATE + " DATE NOT NULL, " +
                COLUMN_ORDERS_STATUS + " TEXT NOT NULL, " +
                COLUMN_ORDERS_IGV + " DOUBLE NOT NULL, " +
                COLUMN_ORDERS_SUBTOTAL + " DOUBLE NOT NULL, " +
                COLUMN_ORDERS_TOTAL + " DOUBLE NOT NULL, " +
                COLUMN_FOREIGN_ORDERS_CLIENT_ID + " INTEGER NOT NULL, " +
                COLUMN_FOREIGN_ORDERS_COMPROBANTE_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + COLUMN_FOREIGN_ORDERS_CLIENT_ID + ") REFERENCES " + TABLE_CLIENT + "(" + COLUMN_CLIENT_ID + "), " +
                "FOREIGN KEY(" + COLUMN_FOREIGN_ORDERS_COMPROBANTE_ID + ") REFERENCES " + TABLE_INVOICE + "(" + COLUMN_INVOICE_ID + "))")

        val createInvoiceTypeTable = ("CREATE TABLE " + TABLE_INVOICE + " (" +
                COLUMN_INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INVOICE_DESCRIPTION + " TEXT NOT NULL, " +
                COLUMN_INVOICE_SERIE + " TEXT NOT NULL, " +
                COLUMN_INVOICE_CORRELATIVO + " TEXT NOT NULL)")


        db.execSQL(createPersonTable)
        db.execSQL(createUserTable)
        db.execSQL(createClientTable)
        db.execSQL(createVendorTable)
        db.execSQL(createCategoryTable)
        db.execSQL(createProductTable)
        db.execSQL(createInventoryTable)
        db.execSQL(createBrandTable)
        db.execSQL(createOrdersTable)
        db.execSQL(createOrderDetailTable)
        db.execSQL(createReturnTable)
        db.execSQL(createDeliveryTable)
        db.execSQL(createInvoiceTypeTable)

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER_DETAIL)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RETURN)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DELIVERY)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVOICE)
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
    fun insertProduct(name: String, price: Double, description: String, stock: Int, categoryId: Int, brandId: Int, image: ByteArray?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_PRODUCT_NAME, name)
            put(COLUMN_PRODUCT_PRICE, price)
            put(COLUMN_PRODUCT_DESCRIPTION, description)
            put(COLUMN_PRODUCT_STOCK, stock)
            put(COLUMN_FOREIGN_CATEGORY_ID, categoryId)
            put(COLUMN_FOREIGN_BRAND_ID, brandId)
            put(COLUMN_PRODUCT_IMAGE, image)
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

    fun getAllProducts(): List<Product> {
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
                if (stock > 0) { // Filtramos productos con stock mayor a 0
                    products.add(Product(id, name, price, description, stock, imageBlob))
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return products
    }

    private val db: SQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(
        context.getDatabasePath("mydatabase"), null
    )

    // Método para insertar un pedido
    fun insertOrder(clientId: Int, subtotal: Double, igv: Double, total: Double, comprobanteId: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ORDERS_DATE, getCurrentDate()) // Puedes definir getCurrentDate() para obtener la fecha actual
            put(COLUMN_ORDERS_STATUS, "PENDING") // Estado por defecto, puede cambiarse si es necesario
            put(COLUMN_ORDERS_IGV, igv)
            put(COLUMN_ORDERS_SUBTOTAL, subtotal)
            put(COLUMN_ORDERS_TOTAL, total)
            put(COLUMN_FOREIGN_ORDERS_CLIENT_ID, clientId)
            put(COLUMN_FOREIGN_ORDERS_COMPROBANTE_ID, comprobanteId)
        }

        // Insertamos el pedido y obtenemos el ID del nuevo pedido
        val orderId = db.insert(TABLE_ORDERS, null, values)
        db.close()
        return orderId
    }

    fun getCurrentDate(): String {
        return LocalDate.now().toString()  // Esto devuelve la fecha en formato "YYYY-MM-DD"
    }

    fun insertOrderDetails(orderId: Long, productId: Int, unitCost: Double, units: Int, discount: Double, total: Double) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FOREIGN_DETAIL_ORDER_ID, orderId)
            put(COLUMN_FOREIGN_DETAIL_PRODUCT_ID, productId)
            put(COLUMN_ORDER_DETAIL_UNITS, units)
            put(COLUMN_ORDER_DETAIL_UNIT_COST, unitCost)
            put(COLUMN_ORDER_DETAIL_DISCOUNT, discount)
            put(COLUMN_ORDER_DETAIL_TOTAL, total)
        }

        // Insertamos los detalles del pedido
        db.insert(TABLE_ORDER_DETAIL, null, values)
        db.close()
    }

    fun updateProductStock(productId: Int, newStock: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("stock", newStock) // La columna "stock" debería existir en la tabla de productos
        }

        // Actualizamos el stock del producto
        db.update(TABLE_PRODUCT, values, "product_id = ?", arrayOf(productId.toString()))
        db.close()
    }

    companion object {
        private const val DATABASE_VERSION = 6
        private const val DATABASE_NAME = "AppStoreDB.db"

        //Tabla Persona
        private const val TABLE_PERSON = "person"
        private const val COLUMN_DOCUMENT = "document"
        private const val COLUMN_DOCUMENT_TYPE = "document_type"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_LAST_NAME = "last_name"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_ADDRESS = "address"

        //Tabla Usuario
        private const val TABLE_USER = "user"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        //Tabla cliente
        private const val TABLE_CLIENT = "client"
        private const val COLUMN_CLIENT_ID = "client_id"

        //Tabla Vendedor
        private const val TABLE_VENDOR = "vendor"
        private const val COLUMN_VENDOR_ID = "vendor_id"

        //Tabla categoria
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

        // Definición de la tabla Detalle_pedido
        private const val TABLE_ORDER_DETAIL = "order_detail"
        private const val COLUMN_ORDER_DETAIL_ID = "order_detail_id"
        private const val COLUMN_ORDER_DETAIL_UNITS = "units"
        private const val COLUMN_ORDER_DETAIL_UNIT_COST = "unit_cost"
        private const val COLUMN_ORDER_DETAIL_DISCOUNT = "discount"
        private const val COLUMN_ORDER_DETAIL_TOTAL = "total"
        private const val COLUMN_FOREIGN_DETAIL_ORDER_ID = "order_id"
        private const val COLUMN_FOREIGN_DETAIL_PRODUCT_ID = "product_id"

        // Definición de la tabla Devolucion
        private const val TABLE_RETURN = "return"
        private const val COLUMN_RETURN_ID = "return_id"
        private const val COLUMN_RETURN_QUANTITY = "quantity"
        private const val COLUMN_RETURN_REASON = "reason"
        private const val COLUMN_RETURN_DATE = "return_date"
        private const val COLUMN_FOREIGN_RETURN_PRODUCT_ID = "product_id"
        private const val COLUMN_FOREIGN_RETURN_ORDER_ID = "order_id"

        // Definición de la tabla Entrega
        private const val TABLE_DELIVERY = "delivery"
        private const val COLUMN_DELIVERY_ID = "delivery_id"
        private const val COLUMN_DELIVERY_TYPE = "delivery_type"
        private const val COLUMN_DELIVERY_DATE = "delivery_date"
        private const val COLUMN_DELIVERY_STATUS = "delivery_status"
        private const val COLUMN_FOREIGN_DELIVERY_ORDER_ID = "order_id"

        // Definición de la tabla Pedido
        private const val TABLE_ORDERS = "orders"
        private const val COLUMN_ORDERS_ID = "order_id"
        private const val COLUMN_ORDERS_DATE = "order_date"
        private const val COLUMN_ORDERS_STATUS = "order_status"
        private const val COLUMN_ORDERS_IGV = "igv"
        private const val COLUMN_ORDERS_SUBTOTAL = "subtotal"
        private const val COLUMN_ORDERS_TOTAL = "total"
        private const val COLUMN_FOREIGN_ORDERS_CLIENT_ID = "client_id"
        private const val COLUMN_FOREIGN_ORDERS_COMPROBANTE_ID = "comprobante_id"

        // Definición de la tabla Tipo_comprobante
        private const val TABLE_INVOICE = "invoice"
        private const val COLUMN_INVOICE_ID = "invoice_id"
        private const val COLUMN_INVOICE_DESCRIPTION = "description"
        private const val COLUMN_INVOICE_SERIE = "serie"
        private const val COLUMN_INVOICE_CORRELATIVO = "correlativo"
    }
}