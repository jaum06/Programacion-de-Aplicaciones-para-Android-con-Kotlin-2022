package com.zlasher.cursobasico

class Person(var name: String = "Anonimo", var passport: String? = null, var height: Float = 1.6f) {

    var alive = true

    fun Person() {
        name = "Juan"
        passport = "asd213"
    }

    fun die() {
        alive = false
    }

    /*fun checkPolice(fn: (Float) -> Boolean): Boolean {
        return fn(height)
    }*/
}