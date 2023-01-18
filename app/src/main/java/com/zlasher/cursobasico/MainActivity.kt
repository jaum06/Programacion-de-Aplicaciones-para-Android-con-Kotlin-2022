package com.zlasher.cursobasico

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var mainContext: Context
    }

    private lateinit var pokemon: Pokemon
    private lateinit var waterPokemon: WaterPokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun createNewPokemon(v: View) {

        val etName = findViewById<EditText>(R.id.etName)
        val etAttackPower = findViewById<EditText>(R.id.etAttackPower)
        pokemon = Pokemon()

        if (!etName.text.isNullOrEmpty() && !etAttackPower.text.isNullOrEmpty()) {
            pokemon.Pokemon(etName.text.toString(), etAttackPower.text.toString().toFloat())
        }

        val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
        ivPokemon.setImageResource(R.mipmap.pokemon)

        val tvPokemon = findViewById<TextView>(R.id.tvPokemon)
        loadDataPokemon(tvPokemon, pokemon)
    }

    private fun loadDataPokemon(tvPokemon: TextView, pokemon: Pokemon) {

        var description: String = ""
        description = pokemon.getName()
        description += " (AP: " + pokemon.getAttackPower().toInt()
        description += " - L:" + pokemon.getLife().toInt() + ")"

        tvPokemon.text = description
    }

    fun createNewWaterPokemon(v: View) {

        val etWaterName = findViewById<EditText>(R.id.etWaterName)
        val etWaterAttackPower = findViewById<EditText>(R.id.etWaterAttackPower)
        val etWaterMaxResistence = findViewById<EditText>(R.id.etWaterMaxResistence)

        waterPokemon = WaterPokemon()

        if (!etWaterName.text.isNullOrEmpty() && !etWaterAttackPower.text.isNullOrEmpty()) {
            waterPokemon.WaterPokemon(
                etWaterName.text.toString(),
                etWaterAttackPower.text.toString().toFloat(),
                etWaterMaxResistence.text.toString().toInt()
            )
        }
        val ivWaterPokemon = findViewById<ImageView>(R.id.ivWaterPokemon)
        ivWaterPokemon.setImageResource(R.mipmap.squirtle)

        val tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterPokemon)
    }

    fun cureWaterPokemon(v: View) {
        waterPokemon.cure()
        val tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterPokemon)
    }

    fun sayHiWaterPokemon(v: View) {
        waterPokemon.sayHi()
    }

    fun evolveWaterPokemon(v: View) {

        val etEvolveWaterPokemon = findViewById<EditText>(R.id.etNewNameWater)
        waterPokemon.evolve(etEvolveWaterPokemon.text.toString())

        val ivWaterPokemon = findViewById<ImageView>(R.id.ivWaterPokemon)
        ivWaterPokemon.setImageResource(R.mipmap.wartortle)

        val tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterPokemon)
    }
}
