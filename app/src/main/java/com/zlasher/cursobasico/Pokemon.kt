package com.zlasher.cursobasico

class Pokemon(
    private var name: String = "Pok",
    var attackPower: Float = 30f,
    var life: Float = 100f
) {

    fun Pokemon(name: String, attackPower: Float) {

        this.name = name
        this.attackPower = attackPower
        this.life = 100f
    }
}