package com.example.miprimerapp

import android.content.Context
import androidx.appcompat.app.AlertDialog

class Utils {
    companion object {
        fun mostrarAlerta(context: Context, titulo: String, mensaje: String) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(titulo)
            builder.setMessage(mensaje)
            builder.setPositiveButton("Entendido") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }
}