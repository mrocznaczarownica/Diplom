package com.example.diplom.usersActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.diplom.R
import com.example.diplom.database.DBHelper
import com.example.diplom.database.Tarif

class CatalogActivity : AppCompatActivity() {

    private lateinit var productList: ListView
    private lateinit var productAdapter: ArrayAdapter<Tarif>
    private var products: ArrayList<Tarif> = ArrayList()
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        // Инициализируем helper для работы с базой данных
        dbHelper = DBHelper(this)

        // Загружаем продукты из базы данных в каталог
        loadProducts()

        // Инициализируем ListView и адаптер
        productList = findViewById(R.id.product_list)
        productAdapter = ArrayAdapter(this, R.layout.product_item, R.id.product_name, products)

        // Задаем адаптер для ListView
        productList.adapter = productAdapter

        // Обрабатываем клик на элементе ListView
        productList.setOnItemClickListener { _, _, index, _ ->
            // Создаем новый экземпляр продукта на основе выбранного элемента списка
            val selectedProduct = products[index]

            // Открываем окно с деталями продукта
            openProductDetail(selectedProduct)
        }
    }

    private fun loadProducts() {
        // Читаем продукты из базы данных
        // Читаем продукты из базы данных
        val db = dbHelper.readableDatabase
        /*val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_PRICE),
            null, null, null, null, null
        )

        // Добавляем каждый продукт в список
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
            val price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
            val product = Product(id, name, description, price)
            products.add(product)
        }
        cursor.close()*/
    }

    private fun openProductDetail(product: Tarif) {
        // Открываем окно с деталями продукта
        // (этот код нужно реализовать)
    }
}