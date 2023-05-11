package cryptography

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

const val MARKER = "000000000000000000000011"

fun main() {
    do {
        println("Task (hide, show, exit):")
        val task = readln()
        when (task) {
            "hide" -> hide()
            "show" -> show()
            "exit" -> println("Bye!")
            else -> println("Wrong task: $task")
        }
    } while (task != "exit")
}