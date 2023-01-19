package com.zlasher.cursobasico

class FirePokemon(name: String = "Pok", attackPower: Float = 30f, life: Float = 100f) :
    Pokemon(name, attackPower, life) {

    private var ballTemperature: Int = 90
    lateinit var ball: ballFire
    var numBall: Int = 0

    fun FirePokemon(name: String, attackPower: Float, ballTemperature: Int) {
        this.name = name
        this.attackPower = attackPower
        this.life = 100f
        this.ballTemperature = ballTemperature
        //this.sayHi()
    }

    override fun attack() {
        //Toast.makeText(mainContext, "Ataque con bola de fuego!!!", Toast.LENGTH_LONG).show()
        super.attack()
        println("Ataque con bola de fuego!!!")
        println("Bola ${++numBall}")
        ball = ballFire(ballTemperature)
        ball.throwBall()
    }
}

class ballFire(var temperature: Int = 100) {
    fun throwBall() {
        println("Tirando bola con temperatura de $temperature")
    }
}