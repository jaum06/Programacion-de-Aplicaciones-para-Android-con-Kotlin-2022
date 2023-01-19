package com.zlasher.cursobasico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var pokemon: Pokemon
    private lateinit var waterPokemon: WaterPokemon
    private lateinit var firePokemon: FirePokemon
    private lateinit var plantPokemon: PlantPokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnBatalla = findViewById<Button>(R.id.btnBatalla)
        btnBatalla.setOnClickListener {
            fight(waterPokemon, firePokemon)
        }
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

    fun createNewFirePokemon(v: View) {

        val etFireName = findViewById<EditText>(R.id.etFireName)
        val etFireAttackPower = findViewById<EditText>(R.id.etFireAttackPower)
        val etFireBallTemperature = findViewById<EditText>(R.id.etFireBallTemperature)

        firePokemon = FirePokemon()

        if (!etFireName.text.isNullOrEmpty() && !etFireAttackPower.text.isNullOrEmpty()) {
            firePokemon.FirePokemon(
                etFireName.text.toString(),
                etFireAttackPower.text.toString().toFloat(),
                etFireBallTemperature.text.toString().toInt()
            )
        }
        val ivFirePokemon = findViewById<ImageView>(R.id.ivFirePokemon)
        ivFirePokemon.setImageResource(R.mipmap.charmander)

        val tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePokemon)
    }

    fun cureFirePokemon(v: View) {
        firePokemon.cure()
        val tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePokemon)
    }

    fun sayHiFirePokemon(v: View) {
        firePokemon.sayHi()
    }

    fun evolveFirePokemon(v: View) {

        val etEvolveFirePokemon = findViewById<EditText>(R.id.etNewNameFire)
        firePokemon.evolve(etEvolveFirePokemon.text.toString())

        val ivFirePokemon = findViewById<ImageView>(R.id.ivFirePokemon)
        ivFirePokemon.setImageResource(R.mipmap.charmeleon)

        val tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePokemon)
    }

    fun createNewPlantPokemon(v: View) {

        val etPlantName = findViewById<EditText>(R.id.etPlantName)
        val etPlantAttackPower = findViewById<EditText>(R.id.etPlantAttackPower)
        val etRegenerationLife = findViewById<EditText>(R.id.etRegenerationLife)

        plantPokemon = PlantPokemon()

        if (!etPlantName.text.isNullOrEmpty() && !etPlantAttackPower.text.isNullOrEmpty()) {
            plantPokemon.PlantPokemon(
                etPlantName.text.toString(),
                etPlantAttackPower.text.toString().toFloat(),
                etRegenerationLife.text.toString().toInt()
            )
        }
        val ivPlantPokemon = findViewById<ImageView>(R.id.ivPlantPokemon)
        ivPlantPokemon.setImageResource(R.mipmap.vulbasaur)

        val tvPlantPokemon = findViewById<TextView>(R.id.tvPlantPokemon)
        loadDataPokemon(tvPlantPokemon, plantPokemon)
    }

    fun curePlantPokemon(v: View) {
        plantPokemon.cure()
        val tvPlantPokemon = findViewById<TextView>(R.id.tvPlantPokemon)
        loadDataPokemon(tvPlantPokemon, plantPokemon)
    }

    fun sayHiPlantPokemon(v: View) {
        plantPokemon.sayHi()
    }

    fun sayByeButton(v: View) {
        plantPokemon.sayBye()
    }

    fun evolvePlantPokemon(v: View) {

        val etNewNamePlant = findViewById<EditText>(R.id.etNewNamePlant)
        plantPokemon.evolve(etNewNamePlant.text.toString())

        val ivPlantPokemon = findViewById<ImageView>(R.id.ivPlantPokemon)
        ivPlantPokemon.setImageResource(R.mipmap.ivysaur)

        val tvPlantPokemon = findViewById<TextView>(R.id.tvPlantPokemon)
        loadDataPokemon(tvPlantPokemon, plantPokemon)
    }

    private fun fight(p1: Pokemon, p2: Pokemon) {

        val mltBatalla = findViewById<EditText>(R.id.mltBatalla)
        mltBatalla.setText("")
        var text = "${p1.getName()} (${p1.getLife().toInt()}) Vs. ${p2.getName()} (${
            p2.getLife().toInt()
        })"

        while (p1.getLife() > 0 && p2.getLife() > 0) {
            text += "\n${p1.getName()} ataca!"
            p1.attack()
            p2.setLife(p2.getLife() - p1.getAttackPower())
            text += "\n${p1.getName()} (${p1.getLife().toInt()}) Vs. ${p2.getName()} (${
                p2.getLife().toInt()
            })"
            if (p2.getLife() > 0) {
                text += "\n${p2.getName()} ataca!"
                p2.attack()
                p1.setLife(p1.getLife() - p2.getAttackPower())
                text += "\n${p1.getName()} (${p1.getLife().toInt()}) Vs. ${p2.getName()} (${
                    p2.getLife().toInt()
                })"
            }
        }

        text += "\n\nEL CAMPEÓN ES: "
        if (p1.getLife() > 0) text += p1.getName()
        else text += p2.getName()

        mltBatalla.setText(text)
    }
}
