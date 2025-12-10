package com.example.miprimerapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ProductoAdapter(context: Context, private val lista: List<Producto>) : 
    ArrayAdapter<Producto>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false)
        val producto = getItem(position)

        val tvNombre = view.findViewById<TextView>(R.id.tvNombreProducto)
        val tvCodigo = view.findViewById<TextView>(R.id.tvCodigo)
        val tvCategoria = view.findViewById<TextView>(R.id.tvCategoria)

        // Asignar datos [cite: 464, 465, 466, 467]
        tvNombre.text = producto?.nombre
        tvCodigo.text = producto?.codigo_barra
        tvCategoria.text = producto?.categoria

        return view
    }
}