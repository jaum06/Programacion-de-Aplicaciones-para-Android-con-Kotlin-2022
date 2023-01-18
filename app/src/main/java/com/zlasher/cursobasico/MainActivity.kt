package com.zlasher.cursobasico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var num: Int
        val jota: Person = Person("Andres", "sad13r")
        var anonimo: Person = Person()
        println(jota.alive)
        println(jota.name)
        println(jota.passport)

        anonimo.Person()
        println(anonimo.alive)
        println(anonimo.name)
        println(anonimo.passport)

        jota.die()
        println(jota.alive)

        val bicho: Pokemon = Pokemon()
        println(bicho.getName())
        println(bicho.getAttackPower())
        println(bicho.setLife(30f))
        println(bicho.getLife())
    }
}
