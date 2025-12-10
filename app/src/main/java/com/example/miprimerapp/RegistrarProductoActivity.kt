package com.example.miprimerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class RegistrarProductoActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_producto)

        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnVolverMenu = findViewById<Button>(R.id.btnVolverMenu)

        btnVolverMenu.setOnClickListener {
            finish() // Cierra la actividad actual y vuelve a la anterior (MenuPrincipal)
        }
        
        btnGuardar.setOnClickListener {
            // Obtenemos referencias a los campos dentro del layout
            val codigo = findViewById<EditText>(R.id.etCodigo).text.toString().trim()
            val nombre = findViewById<EditText>(R.id.etNombre).text.toString().trim()
            val precioStr = findViewById<EditText>(R.id.etPrecio).text.toString().trim()
            val categoria = findViewById<EditText>(R.id.etCategoria).text.toString().trim()
            val url = findViewById<EditText>(R.id.etUrl).text.toString().trim()

            // 1. Validaciones de campos vacíos [cite: 437]
            if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || categoria.isEmpty()) {
                Utils.mostrarAlerta(this, "Faltan datos", "Por favor complete los campos obligatorios.")
                return@setOnClickListener
            }

            // 2. Validación de código único (Requisito crítico) [cite: 456]
            validarYGuardar(codigo, nombre, precioStr.toInt(), categoria, url)
        }
    }

    private fun validarYGuardar(codigo: String, nombre: String, precio: Int, cat: String, url: String) {
        db.collection("Productos") // [cite: 470]
            .whereEqualTo("codigo_barra", codigo)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    // Ya existe
                    Utils.mostrarAlerta(this, "Error", "El código de barra $codigo ya existe en la base de datos.")
                } else {
                    // No existe, procedemos a guardar
                    guardarEnFirestore(codigo, nombre, precio, cat, url)
                }
            }
            .addOnFailureListener {
                Utils.mostrarAlerta(this, "Error de Conexión", "No se pudo verificar el código.")
            }
    }

    private fun guardarEnFirestore(codigo: String, nombre: String, precio: Int, cat: String, url: String) {
        val nuevoProducto = Producto(codigo, nombre, precio, cat, url)
        
        db.collection("Productos").add(nuevoProducto)
            .addOnSuccessListener {
                Utils.mostrarAlerta(this, "Éxito", "Producto registrado correctamente.")
                limpiarCampos()
            }
            .addOnFailureListener { e ->
                Utils.mostrarAlerta(this, "Error", "Error al guardar: ${e.message}")
            }
    }

    private fun limpiarCampos() {
        findViewById<EditText>(R.id.etCodigo).text.clear()
        findViewById<EditText>(R.id.etNombre).text.clear()
        findViewById<EditText>(R.id.etPrecio).text.clear()
        findViewById<EditText>(R.id.etCategoria).text.clear()
        findViewById<EditText>(R.id.etUrl).text.clear()
    }
}