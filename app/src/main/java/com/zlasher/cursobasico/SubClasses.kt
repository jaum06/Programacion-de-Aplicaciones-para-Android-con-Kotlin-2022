package com.zlasher.cursobasico

class SubClasses {

    private var name = "Padre"
    fun presentar(): String {
        return name
    }

    class Anidada {

        private val nameAnidada = "Anidada"
        fun presentar(): String {
            return nameAnidada
        }
    }

    inner class Interna{

        private val nameInterna ="Interna"
        fun presentar():String{return "Hola, soy ${nameInterna},hija de ${name}"}
    }
}