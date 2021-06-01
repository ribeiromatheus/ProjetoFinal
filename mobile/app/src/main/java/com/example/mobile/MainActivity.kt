package com.example.mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mobile.api.ApiFactory
import com.example.mobile.api.Category
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("log", Context.MODE_PRIVATE)

        val pattern = "dd/MM/yyyy HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.format(Date())

        sharedPreferences.edit().putString("last_access", date.toString()).apply()

        updateLastAccess(sharedPreferences)
    }

    override fun onResume() {
        super.onResume()

        showProductsAndCategories()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.product -> startActivity(Intent(this, ProductRegistrationActivity::class.java))
            R.id.category -> startActivity(Intent(this, CategoryRegistrationActivity::class.java))
            R.id.logs -> startActivity(Intent(this, LogActivity::class.java))
            R.id.showCategories -> showCategories()
            R.id.showProductsAndCategories -> showProductsAndCategories()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showProductsAndCategories() {
        val service = ApiFactory.makeApiService()

        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) {
                service.getProduct()
            }

            val data = response.body()
            if (data != null) {
                for ((index, model) in data.withIndex()) {
                    Log.d("Nome do produto: ", model.product_name)
                    Log.d("Preço do produto: ", model.price)
                    Log.d("Categoria do produto: ", model.category_name)
                }
            }
        }
    }

    private fun showCategories() {
        val service = ApiFactory.makeApiService()

        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) {
                service.getCategory()
            }

            val data = response.body()
            if (data != null) {
                for ((index, model) in data.withIndex()) {
                    Log.d("Categorias: ", model.category_name)
                }
            }
        }
    }

    fun updateLastAccess(sharedPreferences: SharedPreferences) {
        sharedPreferences.getString("last_access", "")
    }
}