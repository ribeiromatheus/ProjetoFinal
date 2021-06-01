package com.example.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mobile.api.ApiFactory
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            authentication()
        }
    }

    private fun authentication() {
        val service = ApiFactory.makeGithubService()

        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) {
                service.login(edtGithubUser.text.toString())
            }

            if (response.isSuccessful) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            } else {
                Toast.makeText(
                    applicationContext,
                    "Nenhum usuário encontrado",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}