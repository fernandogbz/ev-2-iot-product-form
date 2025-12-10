package com.example.miprimerapp

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class ListarProductosActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var listView: ListView
    private val listaProductos = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_productos)

        listView = findViewById(R.id.listViewProductos)
        val btnVolverMenu = findViewById<Button>(R.id.btnVolverMenu)

        btnVolverMenu.setOnClickListener {
            finish() // Cierra la actividad actual y vuelve a la anterior (MenuPrincipal)
        }

        cargarDatos()
    }

    private fun cargarDatos() {
        db.collection("Productos")
            .get()
            .addOnSuccessListener { result ->
                listaProductos.clear()
                for (document in result) {
                    val prod = document.toObject(Producto::class.java)
                    listaProductos.add(prod)
                }
                
                // Usamos nuestro adaptador bonito
                val adapter = ProductoAdapter(this, listaProductos)
                listView.adapter = adapter
            }
            .addOnFailureListener {
                Utils.mostrarAlerta(this, "Error", "No se pudieron cargar los productos.")
            }
    }
}