package com.example.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_log.*

class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        val sharedPreferences = getSharedPreferences("log", MODE_PRIVATE)

        val lastAccess = sharedPreferences.getString("last_access", "")

        val logs = ArrayList<String>()

        if (lastAccess != null) {
            logs.add("$lastAccess - último acesso no aplicativo")
        }

        val logsList = ArrayAdapter(this, android.R.layout.simple_list_item_1, logs)

        lstLogs.adapter = logsList
    }
}