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

        val fecha = "01/01/2023"
        var nombre = "Juan"
        var vip = false
        var saludo = "Hola " + nombre

        var dia = fecha.subSequence(0, 2).toString().toInt()
        if (dia == 1) ingresar_sueldo()

        var mes = fecha.subSequence(3, 5).toString().toInt()

        when (mes) {
            1 -> println("En enero hay la super oferta del 7% de interés")
            2, 3 -> println("En invierno no hay ofertas de inversiones")
            4, 5, 6 -> println("En primavera hay ofertas de inversiones con el 1.5% de interés")
            7, 8, 9 -> println("En verano hay ofertas de inversiones con el 2.5% de interés")
            10, 11, 12 -> println("En otoño hay ofertas de inversiones con el 3.5% de interés")
            else -> println("El mes es incorrecto")
        }

        var a1 = true
        var b1 = true
        var c1 = false
        var d1 = false

        a1 && b1
        a1 || b1

        a1 && c1
        a1 && c1

        !d1

        if (vip) saludo += " te queremos mucho"
        else saludo += " quieres ser vip? paga la cuota"

        mostrar_saldo()

        nombre = "Andres"
        println(saludo)

        var pin = 1234
        var intentos = 0
        var clave_ingresada = 1234

        do {
            println("Ingrese el PIN")
            println("Clave ingresada: ${clave_ingresada++}")
            if (clave_ingresada == pin) break
            intentos++
        } while (intentos < 3 && clave_ingresada != pin)

        if (intentos == 3) println("Tarjeta bloqueda")

        mostrar_saldo()
        ingresar_dinero(50.5f)
        retirar_dinero(40f)

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
        saldo++

        a == b
        a != b
        a > b
        a < b
        a >= b
        a <= b
    }

    fun mostrar_saldo() {
        println("Tienes $saldo $moneda")
    }

    fun ingresar_sueldo() {
        saldo += sueldo
        println("Se ha ingresado tu sueldo de $sueldo $moneda")
        mostrar_saldo()
    }

    fun ingresar_dinero(cantidad: Float) {
        saldo += cantidad
        println("Se ha ingresado $cantidad $moneda")
        mostrar_saldo()
    }

    fun retirar_dinero(cantidad: Float) {
        saldo -= cantidad
        println("Se ha retirado $cantidad $moneda")
        mostrar_saldo()
    }
}