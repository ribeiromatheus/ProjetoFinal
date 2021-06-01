package com.example.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mobile.api.ApiFactory
import com.example.mobile.api.Category
import kotlinx.android.synthetic.main.activity_category_registration.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryRegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_registration)

        btnSubmit.setOnClickListener {
            create()
            finish()
        }
    }

    private fun create() {
        val service = ApiFactory.makeApiService()

        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) {
                service.createCategory(Category(edtName.text.toString()))
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