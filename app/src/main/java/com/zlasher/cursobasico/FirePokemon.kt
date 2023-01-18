package com.zlasher.cursobasico

class FirePokemon(name: String = "Pok", attackPower: Float = 30f, life: Float = 100f) :
    Pokemon(name, attackPower, life) {

    private var ballTemperature: Int = 90

    fun FirePokemon(name: String, attackPower: Float, ballTemperature: Int) {
        this.name = name
        this.attackPower = attackPower
        this.life = 100f
        this.ballTemperature = ballTemperature
        //this.sayHi()
    }

    override fun attack() {
        //Toast.makeText(mainContext, "Ataque con bola de fuego!!!", Toast.LENGTH_LONG).show()
        println("Ataque con bola de fuego!!!")
    }
}