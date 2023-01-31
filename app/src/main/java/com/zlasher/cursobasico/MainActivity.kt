package com.zlasher.cursobasico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var cellSelectedX = 0
    private var cellSelectedY = 0
    private lateinit var board: Array<IntArray>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initScreenGame()
        resetBoard()
        setFirstPosition()
    }

    fun checkCellClicked(v: View) {

        val name = v.tag.toString()
        val x = name.subSequence(1, 2).toString().toInt()
        val y = name.subSequence(2, 3).toString().toInt()

        checkCell(x, y)
    }

    private fun checkCell(x: Int, y: Int) {

        val difX = kotlin.math.abs(x - cellSelectedX)
        val difY = kotlin.math.abs(y - cellSelectedY)

        var checkTrue = false

        if (board[x][y] == 1) checkTrue = false
        else if (difX == 1 && difY == 2) checkTrue = true
        else if (difX == 2 && difY == 1) checkTrue = true

        if (checkTrue) selectCell(x, y)
    }

    private fun resetBoard() {

        /*
            0 -> Esta libre
            1 -> Casilla marcada
            2 -> Es un bonus
            9 -> Es una opción del movimiento actual
        */

        board = Array(8) { IntArray(8) { 0 } }
    }

    private fun setFirstPosition() {

        val x = (0..7).random()
        val y = (0..7).random()

        cellSelectedX = x
        cellSelectedY = y
        selectCell(x, y)
    }

    private fun selectCell(x: Int, y: Int) {

        board[x][y] = 1
        paintHorseCell(cellSelectedX, cellSelectedY, "previous_cell")
        cellSelectedX = x
        cellSelectedY = y
        paintHorseCell(x, y, "selected_cell")
    }

    private fun paintHorseCell(x: Int, y: Int, color: String) {

        val imageView: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        imageView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                resources.getIdentifier(color, "color", packageName)
            )
        )
        imageView.setImageResource(R.drawable.ic_horse)
    }

    private fun initScreenGame() {

        hideMessage()
        //setSizeBoard()
    }

    private fun hideMessage() {

        val clmessage = findViewById<ConstraintLayout>(R.id.clmessage)
        clmessage.visibility = View.INVISIBLE
    }


    /*private fun setSizeBoard() {

        var imageView: ImageView
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val widthDp = (width / resources.displayMetrics.density)
        val lateralMarginsDp = 10
        val widthCell = ((widthDp - lateralMarginsDp) / 8)
        val heightCell = widthCell

        for (i in 0..7) {
            for (j in 0..7) {
                imageView = findViewById(resources.getIdentifier("c$i$j", "id", packageName))
                val height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    heightCell,
                    resources.displayMetrics
                ).toInt()
                val width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    heightCell,
                    resources.displayMetrics
                ).toInt()
                imageView.layoutParams(TableRow.LayoutParams(width, height))
            }
        }
    }*/
}


