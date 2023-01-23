package com.zlasher.cursobasico

enum class Dias(val laboral: Boolean, val jornada: Int) {

    LUNES(true, 8),
    MARTES(true, 8),
    MIERCOLES(true, 8),
    JUEVES(true, 8),
    VIERNES(true, 4),
    SABADO(false, 0),
    DOMINGO(false, 0);

    fun saludo(): String {
        when (this) {
            LUNES -> return "Empezando con alegría!!!"
            MARTES -> return "Ya queda menos!!!"
            MIERCOLES -> return "Sabías que los miércoles son los días más productivos?"
            JUEVES -> return "Esta noche es juernes!!!"
            VIERNES -> return "Hoy es viernes y tu cuerpo lo sabe!!!"
            else -> return "A quemar el findeee!!!"
        }
    }
}