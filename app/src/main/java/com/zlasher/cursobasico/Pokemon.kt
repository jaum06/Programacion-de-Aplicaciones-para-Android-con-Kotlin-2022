package com.zlasher.cursobasico

import android.widget.Toast
import com.zlasher.cursobasico.MainActivity.Companion.mainContext


open class Pokemon(
    protected var name: String = "Pok",
    protected var attackPower: Float = 30f,
    protected var life: Float = 100f
) {

    fun Pokemon(name: String, attackPower: Float) {

        this.name = name
        this.attackPower = attackPower
        this.life = 100f
        this.sayHi()
    }

    internal fun getName(): String {
        return this.name
    }

    internal fun getAttackPower(): Float {
        return this.attackPower
    }

    internal fun getLife(): Float {
        return this.life
    }

    internal fun setLife(life: Float) {
        this.life = life
    }

    fun sayHi() {
        Toast.makeText(mainContext, "Hola!!! soy $name", Toast.LENGTH_LONG).show()
    }

    fun cure() {
        this.life = 100f
    }

    fun evolve(name: String) {
        this.name = name
        this.attackPower *= 1.20f
        //this.sayHi()
    }

    open fun attack() {
        Toast.makeText(mainContext, "Al ataquee", Toast.LENGTH_LONG).show()
    }

}
