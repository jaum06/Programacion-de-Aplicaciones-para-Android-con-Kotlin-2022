package com.zlasher.cursobasico

import android.widget.Toast
import com.zlasher.cursobasico.MainActivity.Companion.mainContext

class WaterPokemon(name: String = "Pok", attackPower: Float = 30f, life: Float = 100f) :
    Pokemon(name, attackPower, life) {

    private var maxResistance: Int = 500
    private var submergedTime: Int = 0

    fun WaterPokemon(name: String, attackPower: Float, maxReistance: Int) {

        this.name = name
        this.attackPower = attackPower
        this.life = 100f
        this.maxResistance = maxResistance
        //this.sayHi()
    }

    fun breathe() {
        this.submergedTime = 0
    }

    fun immerse() {
        this.submergedTime++
    }

    override fun attack() {
        Toast.makeText(mainContext, "Ataque con chorro de agua!!!", Toast.LENGTH_LONG).show()
    }
}