package com.zlasher.cursobasico

class PlantPokemon(name: String = "Pok", attackPower: Float = 30f, life: Float = 100f) :
    Pokemon(name, attackPower, life) {

    private var regenerationLife: Int = 2

    fun PlantPokemon(name: String, attackPower: Float, regenerationLife: Int) {
        this.name = name
        this.attackPower = attackPower
        this.life = 100f
        this.regenerationLife = regenerationLife
        this.sayHi()
    }

    fun regeneration() {
        println("Regenerará ${this.regenerationLife} de vida")
    }

    override fun attack() {

        println("Ataque con hoja filosa")
    }
}