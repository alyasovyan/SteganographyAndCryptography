package cryptography

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**show step*/
fun show() {
    println("Input image file:")
    val inputNamePath = readln()

    println("Password:")
    val password = readln()

    val file = File(inputNamePath)
    if (file.exists()) {
        readFile(inputNamePath, file, password)
    } else {
        println("Can't read input file!")
    }
}

fun readFile(inputNamePath: String, file: File, password: String) {
    println("Input Image: $inputNamePath")

    val bufferedImage: BufferedImage = ImageIO.read(file)
    var binaryString = ""
    loop@for (height in 0 until bufferedImage.height) {
        for (width in 0 until bufferedImage.width) {
            val color = Color(bufferedImage.getRGB(width, height))
            binaryString += Integer.toBinaryString(color.blue).last()

            if(binaryString.indexOf(MARKER) != -1) break@loop else continue
        }
    }

    val binaryPassword = convertToBinary(password)
    val unCryptString = cryptBiteString(binaryString, binaryPassword)
    val message = unCryptString
        .slice(0 until binaryString.length - 24)
        .chunked(8)
        .map { it.toUByte(2).toByte() }
        .toByteArray()
        .decodeToString()

    println("Message:")
    println(message)
}

