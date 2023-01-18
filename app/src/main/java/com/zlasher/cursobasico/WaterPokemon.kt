package com.zlasher.cursobasico

import android.view.View
import android.widget.EditText

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
}