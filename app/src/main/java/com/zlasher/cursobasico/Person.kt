package com.zlasher.cursobasico

class Person(var name: String = "Anonimo", var passport: String? = null) {

    var alive = true

    fun Person() {
        name = "Juan"
        passport = "asd213"
    }

    fun die() {
        alive = false
    }
}