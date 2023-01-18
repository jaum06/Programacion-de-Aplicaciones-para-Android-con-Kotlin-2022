package com.zlasher.cursobasico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var pokemon: Pokemon

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


}
