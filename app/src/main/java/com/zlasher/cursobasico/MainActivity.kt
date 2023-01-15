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

        var a = 5 + 5
        var b = 10 - 2
        var c = 3 * 4
        var d = 10 / 5
        var e = 10 % 3
        var f = 10 / 6

        var aPreIncremento = 5
        var bPreDecremento = 5
        var cPostIncremento = 5
        var dPostDecremento = 5

        println(aPreIncremento)
        println(++aPreIncremento) //Incrementa primero, luego regresa. Salida 6
        println(aPreIncremento)

        println(bPreDecremento)
        println(--bPreDecremento) //Primero decrementa, luego regresa. Salida 4
        println(bPreDecremento)

        println(cPostIncremento)
        println(cPostIncremento++) //Primero regresa, luego incrementa. Salida 5
        println(cPostIncremento)

        println(dPostDecremento)
        println(dPostDecremento--) //Primero regresa, luego decrementa. Salida 5
        println(dPostDecremento)

        saldo += sueldo
    }
}