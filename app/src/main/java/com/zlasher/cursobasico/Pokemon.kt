package com.zlasher.cursobasico

class Pokemon(
    private var name: String = "Pok",
    private var attackPower: Float = 30f,
    private var life: Float = 100f
) {

    fun Pokemon(name: String, attackPower: Float) {

        this.name = name
        this.attackPower = attackPower
        this.life = 100f
    }

    fun getName(): String {
        return this.name
    }

    fun getAttackPower(): Float {
        return this.attackPower
    }

    fun getLife(): Float {
        return this.life
    }

    fun setLife(life: Float) {
        this.life = life
    }

}