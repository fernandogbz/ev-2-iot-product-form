package com.example.miprimerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        findViewById<Button>(R.id.btnIrRegistrar).setOnClickListener {
            startActivity(Intent(this, RegistrarProductoActivity::class.java))
        }

        findViewById<Button>(R.id.btnIrListar).setOnClickListener {
            startActivity(Intent(this, ListarProductosActivity::class.java))
        }
    }
}