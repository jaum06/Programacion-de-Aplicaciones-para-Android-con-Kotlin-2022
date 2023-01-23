package com.zlasher.cursobasico.tarea

open class Deportista() {

    private var nombre: String = ""
    private var estatura: Float = 0f
    private var peso: Float = 0f
    private var edad: Int = 0

    fun descansar() {
        println("Momento para descansar")
    }
}

class Runner() : Deportista() {

    private var estilo: String = ""
    private var velocidad: Int = 0

    fun correr() {
        println("El deportista esta corriendo con el estilo ${estilo} a una velocidad de ${velocidad} km/h")
    }

    fun competir() {
        println("Voy a correr!!!")
    }
}

class Ciclista() : Deportista() {

    private var tipoBicicleta: String = ""
    private var velocidad: Int = 0

    fun pedalear() {
        println("El ciclista esta en una bicicleta ${tipoBicicleta} y va a una velocidad de ${velocidad} km/h")
    }

    fun competir() {
        println("Voy a pedalear!!!")
    }
}

class Nadador() : Deportista() {

    private var estilo: String = ""
    private var velocidad: Int = 0

    fun nadar() {
        println("El nadador usa el estilo ${estilo} y vaa una velocidad de ${velocidad} km/h")
    }

    fun competir() {
        println("Voy a nadar!!!")
    }
}