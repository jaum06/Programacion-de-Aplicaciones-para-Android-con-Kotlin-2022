package com.zlasher.cursobasico

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.test.runner.screenshot.ScreenCapture
import androidx.test.runner.screenshot.Screenshot.capture
import java.io.File
import java.io.FileOutputStream
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var bitmap: Bitmap? = null
    private var mHandler: Handler? = null
    private var timeInSeconds = 0L
    private var gaming = true
    private var cellSelectedX = 0
    private var cellSelectedY = 0
    private lateinit var board: Array<IntArray>
    private var options = 0
    private var bonus = 0
    private var checkMovement = true

    private var nextLevel = false
    private var level = 1
    private var levelMoves = 0
    private var movesRequired = 0
    private var moves = 0
    private var lives = 1

    private var nameColorBlack = "black_cell"
    private var nameColorWhite = "white_cell"
    private var string_share = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initScreenGame()
        startGame()
    }

    private fun startGame() {

        setLevel()
        setLevelParameters()

        resetBoard()
        clearBoard()

        setBoardLevel()
        setFirstPosition()

        resetTime()
        startTime()
        gaming = true
    }

    private fun startTime() {

        mHandler = Handler(Looper.getMainLooper())
        chronometer.run()
    }

    private fun resetTime() {

        mHandler?.removeCallbacks(chronometer)
        timeInSeconds = 0

        val tvtime = findViewById<TextView>(R.id.tvtime)
        tvtime.text = "00:00"
    }

    private var chronometer: Runnable = object : Runnable {
        override fun run() {
            try {
                if (gaming) {
                    timeInSeconds++
                    updateStopWatchView(timeInSeconds)
                }
            } finally {
                mHandler!!.postDelayed(this, 1000L)
            }
        }

    }

    private fun updateStopWatchView(timeInSeconds: Long) {

        val formattedTime = getFormattedStopWatch((timeInSeconds * 1000))
        val tvtime = findViewById<TextView>(R.id.tvtime)
        tvtime.text = formattedTime
    }

    private fun getFormattedStopWatch(ms: Long): String {

        var milliseconds = ms
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        return "${if (minutes < 10) "0" else ""}$minutes:" +
                "${if (seconds < 10) "0" else ""}$seconds"
    }

    private fun clearBoard() {

        var imageView: ImageView
        val colorBlack = ContextCompat.getColor(
            this,
            resources.getIdentifier(nameColorBlack, "color", packageName)
        )
        val colorWhite = ContextCompat.getColor(
            this,
            resources.getIdentifier(nameColorWhite, "color", packageName)
        )

        for (i in 0..7) {
            for (j in 0..7) {
                imageView = findViewById(resources.getIdentifier("c$i$j", "id", packageName))
                imageView.setImageResource(0)
                if (checkColorCell(i, j) == "black") imageView.setBackgroundColor(colorBlack)
                else imageView.setBackgroundColor(colorWhite)
            }
        }
    }

    fun launchShareGame(v: View) {
        shareGame()
    }

    private fun shareGame() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )

        var ssc: ScreenCapture = capture(this)
        bitmap = ssc.getBitmap()

        if (bitmap != null) {
            var idGame = SimpleDateFormat("yyyy/MM/dd").format(Date())
            idGame = idGame.replace(":", "")
            idGame = idGame.replace("/", "")

            val path = saveImage(bitmap, "${idGame}.jpg")
            val bmpUri = Uri.parse(path)

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri)
            shareIntent.putExtra(Intent.EXTRA_TEXT, string_share)
            shareIntent.type = "image/png"

            val finalShareIntent = Intent.createChooser(
                shareIntent,
                "Selecciona la aplicación en la cual quieres compartir el juego"
            )
            finalShareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(finalShareIntent)
        }
    }

    private fun saveImage(bitmap: Bitmap?, fileName: String): String? {
        if (bitmap == null) {
            return null
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + "/Screenshots"
                )
            }
            val uri = this.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            if (uri != null) {
                this.contentResolver.openOutputStream(uri).use {
                    if (it == null) {
                        return@use
                    }
                    bitmap.compress(Bitmap.CompressFormat.PNG, 85, it)
                    it.flush()
                    it.close()

                    // add pic to gallery
                    MediaScannerConnection.scanFile(this, arrayOf(uri.toString()), null, null)
                }
            }
            return uri.toString()
        }

        val filePath = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES + "/Screenshots"
        ).absolutePath

        val dir = File(filePath)
        if (!dir.exists()) dir.mkdirs()
        val file = File(dir, fileName)
        val fOut = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut)
        fOut.flush()
        fOut.close()

        //add pic to gallery
        MediaScannerConnection.scanFile(this, arrayOf(file.toString()), null, null)
        return filePath
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

        var x = 0
        var y = 0
        var firstPosition = false

        while (firstPosition) {
            x = (0..7).random()
            y = (0..7).random()
            if (board[x][y] == 0) firstPosition = true
            checkOptions(x, y)
            if (options == 0) firstPosition = false
        }
        cellSelectedX = x
        cellSelectedY = y
        selectCell(x, y)
    }

    private fun setLevel() {

        if (nextLevel) {
            level++
            setLives()
        } else {
            lives--
            if (lives < 1) {
                level = 1
                lives = 1
            }
        }
    }

    private fun setLives() {

        when (level) {
            1 -> lives = 1
            2 -> lives = 4
            3 -> lives = 3
            4 -> lives = 3
            5 -> lives = 4
            6 -> lives = 3
            7 -> lives = 5
            8 -> lives = 3
            9 -> lives = 4
            10 -> lives = 5
            11 -> lives = 5
            12 -> lives = 3
            13 -> lives = 4
        }
    }

    private fun setLevelParameters() {
        var tvLive = findViewById<TextView>(R.id.tvlive)
        tvLive.text = lives.toString()


        var tvlevel = findViewById<TextView>(R.id.tvlevel)
        tvlevel.text = level.toString()

        bonus = 0
        var tvBonus = findViewById<TextView>(R.id.tvbonus)
        tvBonus.text = ""

        setLevelMoves()
        moves = levelMoves

        movesRequired = setMovesRequired()
    }

    private fun setLevelMoves() {

        when (level) {
            1 -> levelMoves = 64
            2 -> levelMoves = 56
            3 -> levelMoves = 32
            4 -> levelMoves = 16
            5 -> levelMoves = 48
            6 -> levelMoves = 36
            7 -> levelMoves = 48
            8 -> levelMoves = 49
            9 -> levelMoves = 59
            10 -> levelMoves = 48
            11 -> levelMoves = 64
            12 -> levelMoves = 48
            13 -> levelMoves = 48
        }
    }

    private fun setMovesRequired(): Int {

        var movesRequired = 0
        when (level) {
            1 -> movesRequired = 8
            2 -> movesRequired = 10
            3 -> movesRequired = 12
            4 -> movesRequired = 10
            5 -> movesRequired = 10
            6 -> movesRequired = 12
            7 -> movesRequired = 5
            8 -> movesRequired = 7
            9 -> movesRequired = 9
            10 -> movesRequired = 8
            11 -> movesRequired = 1000
            12 -> movesRequired = 5
            13 -> movesRequired = 5
        }
        return movesRequired
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
        checkOptions(x, y)
        if (moves > 0) {
            checkNewBonus()
            checkGameOver()
        } else showMessage("Has ganado!!!", "Siguiente nivel", false)

    }

    private fun checkGameOver() {

        if (options == 0) {
            if (bonus > 0) {
                checkMovement = false
                paintAllOptions()
            } else showMessage("Game over", "Intena de nuevo", true)

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

        gaming = false
        nextLevel = !gameOver
        val clmessage = findViewById<ConstraintLayout>(R.id.clmessage)
        clmessage.visibility = View.VISIBLE

        val tvtitlemessage = findViewById<TextView>(R.id.tvtitlemessage)
        tvtitlemessage.text = title

        val tvtime = findViewById<TextView>(R.id.tvtime)

        var score = ""
        if (gameOver) {
            "Puntaje: ${levelMoves - moves} / $levelMoves"
            string_share =
                "Este juego me vuelve loco!!! (" + score + ") http://jotajotavm.com/retocaballo"
        } else {
            score = tvtime.text.toString()
            string_share =
                "Vamos!!! Nuevo desafio completado. Level ${level} (" + score + ") http://jotajotavm.com/retocaballo"
        }

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

    private fun setBoardLevel() {

        when (level) {
            2 -> paintLevel2()
            3 -> paintLevel3()
            4 -> paintLevel4()
            5 -> paintLevel5()
        }
    }

    private fun paintColumn(column: Int) {
        for (i in 0..7) {
            board[column][i] = 1
            paintHorseCell(column, i, "previous_cell")
        }
    }

    private fun paintRow(row: Int) {

        for (i in 0..7) {
            board[i][row] = 1
            paintHorseCell(i, row, "previous_cell")
        }

    }

    private fun paintDiagonal() {

        for (i in 0..7) {
            board[i][i] = 1
            paintHorseCell(i, i, "previous_cell")
        }

    }

    private fun paintDiagonalInverse() {

        for (i in 0..7) {
            board[i][abs(i - 7)] = 1
            paintHorseCell(i, abs(i - 7), "previous_cell")
        }

    }

    private fun paintLevel2() {
        paintColumn(6)
    }

    private fun paintLevel3() {

        for (i in 0..7) {
            for (j in 4..7) {
                board[i][j] = 1
                paintHorseCell(j, i, "previous_cell")
            }
        }
    }

    private fun paintLevel4() {
        paintLevel3()
        paintLevel5()
    }

    private fun paintLevel5() {

        for (i in 0..3) {
            for (j in 0..3) {
                board[j][i] = 1
                paintHorseCell(j, i, "previous_cell")
            }
        }
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


    private fun checkOptions(x: Int, y: Int) {

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

        hideMessage(false)
        //setSizeBoard()
    }

    fun launchAction(v: View) {
        hideMessage(true)
    }

    private fun hideMessage(start: Boolean) {

        val clmessage = findViewById<ConstraintLayout>(R.id.clmessage)
        clmessage.visibility = View.INVISIBLE

        if (start) startGame()
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


