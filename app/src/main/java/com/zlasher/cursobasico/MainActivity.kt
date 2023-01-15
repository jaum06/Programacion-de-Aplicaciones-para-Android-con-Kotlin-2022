package com.zlasher.cursobasico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    companion object {

        const val moneda = "EUR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fecha = "14/01/2023"
        var nombre = "Juan"

        nombre = "Andres"
        println(nombre)
    }
}