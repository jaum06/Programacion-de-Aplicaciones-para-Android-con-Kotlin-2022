package com.zlasher.cursobasico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    companion object {

        const val moneda = "EUR"
    }

    var saldo = 300.54f
    var sueldo = 764.82f
    var entero = 62

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fecha = "14/01/2023"
        var nombre = "Juan"
        var vip = false
        var saludo = "Hola " + saldo

        nombre = "Andres"
        println(saludo)
    }
}