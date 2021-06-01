package com.example.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mobile.api.ApiFactory
import com.example.mobile.api.Product
import kotlinx.android.synthetic.main.activity_product_registration.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductRegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_registration)

        showCategories()

        btnSubmit.setOnClickListener {
            create()
            finish()
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
                    Log.d("ID da categoria: ", model.id.toString())
                    Log.d("Nome da categoria: ", model.category_name)
                }
            }
        }
    }

    private fun create() {
        val service = ApiFactory.makeApiService()

        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) {
                service.createProduct(
                    Product(
                        edtName.text.toString(),
                        edtPrice.text.toString(),
                        edtCategory.text.toString().toInt()
                    )
                )
            }

            if (response.isSuccessful) {
                Toast.makeText(
                    applicationContext,
                    "Criado",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Erro",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}