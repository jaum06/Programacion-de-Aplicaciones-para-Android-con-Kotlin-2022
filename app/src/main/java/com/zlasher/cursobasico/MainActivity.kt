package com.zlasher.cursobasico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var cellSelectedX = 0
    private var cellSelectedY = 0
    private lateinit var board: Array<IntArray>
    private var options = 0
    private var bonus = 0
    private var checkMovement = true
    private var levelMoves = 64
    private var moves = 64
    private var movesRequired = 4
    private var nameColorBlack = "black_cell"
    private var nameColorWhite = "white_cell"
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
        for (i in board.indices) {
            println(board[i].contentToString())
        }
        println("------------------------------------------------------------------")
    }

    private fun checkCell(x: Int, y: Int) {

        var checkTrue = true
        if (checkMovement) {
            val difX = kotlin.math.abs(x - cellSelectedX)
            val difY = kotlin.math.abs(y - cellSelectedY)

            checkTrue = false

            if (board[y][x] == 1) checkTrue = false
            else if (difX == 1 && difY == 2) checkTrue = true
            else if (difX == 2 && difY == 1) checkTrue = true

        } else {
            if (board[y][x] != 1) {
                bonus--
                val tvbonus = findViewById<TextView>(R.id.tvbonus)
                tvbonus.text = if (bonus != 0) {
                    "Bonus: $bonus"
                } else {
                    ""
                }
            }
        }
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

        val tvmoves = findViewById<TextView>(R.id.tvmoves)

        moves--
        tvmoves.text = moves.toString()
        growProgressBonus()
        if (board[y][x] == 2) {
            bonus++
            val tvbonus = findViewById<TextView>(R.id.tvbonus)
            tvbonus.text = "Bonus: $bonus"
        }
        board[y][x] = 1
        paintHorseCell(cellSelectedX, cellSelectedY, "previous_cell")
        cellSelectedX = x
        cellSelectedY = y
        clearOptions()
        paintHorseCell(x, y, "selected_cell")
        checkMovement = true
        checkOption(x, y)
        if (moves > 0) {
            checkNewBonus()
            checkGameOver(x, y)
        } else showMessage("Has ganado!!!", "Siguiente nivel", false)

    }

    private fun checkGameOver(x: Int, y: Int) {

        if (options == 0) {
            if (bonus == 0) showMessage("Game over", "Intena de nuevo", true)
            else {
                checkMovement = false
                paintAllOptions()
            }
        }
    }

    private fun paintAllOptions() {

        for (i in 0..7) {
            for (j in 0..7) {
                if (board[j][i] != 1) paintOption(j, i)
                if (board[j][i] == 0) board[j][i] = 9
            }
        }
    }

    private fun showMessage(title: String, action: String, gameOver: Boolean) {

        val clmessage = findViewById<ConstraintLayout>(R.id.clmessage)
        clmessage.visibility = View.VISIBLE

        val tvtitlemessage = findViewById<TextView>(R.id.tvtitlemessage)
        tvtitlemessage.text = title

        val tvtime = findViewById<TextView>(R.id.tvtime)

        val score =
            if (gameOver) "Puntaje: ${levelMoves - moves} / $levelMoves" else tvtime.text.toString()

        val tvscoremessage = findViewById<TextView>(R.id.tvscoremessage)
        tvscoremessage.text = score

        val tvaction = findViewById<TextView>(R.id.tvaction)
        tvaction.text = action
    }

    private fun growProgressBonus() {

        /*val movesDone = levelMoves - moves
        val bonusDone = movesDone / movesRequired
        val movesRest = movesRequired * bonusDone
        val bonusGrow = movesDone - movesRest*/
    }

    private fun checkNewBonus() {

        if (moves % movesRequired == 0) {
            var bonusCellX = 0
            var bonusCellY = 0
            var bonusCell = false

            while (!bonusCell) {
                bonusCellX = (0..7).random()
                bonusCellY = (0..7).random()
                if (board[bonusCellY][bonusCellX] == 0) bonusCell = true
            }
            board[bonusCellY][bonusCellX] = 2
            paintBonusCell(bonusCellX, bonusCellY)
        }
    }

    private fun paintBonusCell(x: Int, y: Int) {

        val imageView: ImageView =
            findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        imageView.setImageResource(R.drawable.bonus)
    }

    private fun clearOption(x: Int, y: Int) {

        val imageView: ImageView =
            findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        if (checkColorCell(x, y) == "black") {
            imageView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    resources.getIdentifier(nameColorBlack, "color", packageName)
                )
            )
        } else {
            imageView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    resources.getIdentifier(nameColorWhite, "color", packageName)
                )
            )
        }
        if (board[x][y] == 1) {
            imageView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    resources.getIdentifier("previous_cell", "color", packageName)
                )
            )
        }
    }

    private fun clearOptions() {

        for (i in 0..7) {
            for (j in 0..7) {
                if (board[i][j] == 2 || board[i][j] == 9) {
                    if (board[i][j] == 9) {
                        board[i][j] = 0
                        clearOption(j, i)
                    }
                }
            }
        }
    }


    private fun checkOption(x: Int, y: Int) {

        options = 0
        checkMove(x, y, 1, -2) // Valida el movimiento a la derecha - arriba largo
        checkMove(x, y, 2, -1) // Valida el movimiento a la derecha largo - arriba
        checkMove(x, y, 1, 2) // Valida el movimiento a la derecha - abajo largo
        checkMove(x, y, 2, 1) // Valida el movimiento a la derecha largo- abajo
        checkMove(x, y, -1, -2) // Valida el movimiento a la izquierda - arriba largo
        checkMove(x, y, -2, -1) // Valida el movimiento a la izquierda largo - arriba
        checkMove(x, y, -1, 2) // Valida el movimiento a la izquierda - abajo largo
        checkMove(x, y, -2, 1) // Valida el movimiento a la izquierda largo - abajo
        val tvoption = findViewById<TextView>(R.id.tvoption)
        tvoption.text = options.toString()
    }

    private fun checkMove(x: Int, y: Int, movX: Int, movY: Int) {

        val optionX = x + movX
        val optionY = y + movY

        if (optionX >= 0 && optionY >= 0 && optionX <= 7 && optionY <= 7) {
            if (board[optionY][optionX] == 0 || board[optionY][optionX] == 2) {
                options++
                paintOption(optionX, optionY)
                if (board[optionY][optionX] == 0) board[optionY][optionX] = 9
            }
        }
    }

    private fun paintOption(x: Int, y: Int) {

        val imageView: ImageView =
            findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        if (checkColorCell(x, y) == "black") {
            imageView.setBackgroundResource(R.drawable.option_black)
        } else {
            imageView.setBackgroundResource(R.drawable.option_white)
        }
    }

    private fun checkColorCell(x: Int, y: Int): String {

        val blackColumn = arrayOf(1, 3, 5, 7)
        val blackRow = arrayOf(0, 2, 4, 6)
        val color =
            if ((blackColumn.contains(x) && blackRow.contains(y) || blackRow.contains(x) && blackColumn.contains(
                    y
                ))
            ) {
                "black"
            } else "white"
        return color
    }

    private fun paintHorseCell(x: Int, y: Int, color: String) {

        val imageView: ImageView =
            findViewById(resources.getIdentifier("c$x$y", "id", packageName))
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


