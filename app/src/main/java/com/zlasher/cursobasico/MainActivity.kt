package com.zlasher.cursobasico

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableRow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var cellSelectedX = 0
    private var cellSelectedY = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initScreenGame()
        setFirstPosition()
    }

    private fun setFirstPosition() {

        val x = (0..7).random()
        val y = (0..7).random()

        cellSelectedX = x
        cellSelectedY = y
        selectCell(x, y, "previous_cell")
        selectCell(x, y, "selected_cell")
    }

    private fun selectCell(x: Int, y: Int, color: String) {

        paintHorseCell(x, y, color)
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


