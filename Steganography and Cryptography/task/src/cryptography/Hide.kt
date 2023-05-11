package cryptography

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**hide step*/
fun hide() {
    println("Input image file:")
    val inputNamePath = readln()

    println("Output image file:")
    val outputNamePath = readln()

    println("Message to hide:")
    val messageToHide = readln()

    println("Password:")
    val password = readln()

    val file = File(inputNamePath)
    if (file.exists()) {
        readAndHideInImage(inputNamePath, outputNamePath, file, messageToHide, password)
    } else {
        println("Can't read input file!")
    }
}

fun readAndHideInImage(inputNamePath: String, outputNamePath: String, file: File, message: String, password: String) {
    println("Input Image: $inputNamePath")
    println("Output Image: $outputNamePath")

    val binaryMessage = convertToBinary(message)
    val binaryPassword = convertToBinary(password)
    val encryptedMessage = cryptBiteString(binaryMessage, binaryPassword)
    val fullEncryptedMessage = encryptedMessage + MARKER

    val bufferedImage: BufferedImage = ImageIO.read(file)
    val countOfPixels = bufferedImage.width * bufferedImage.height
    if(countOfPixels < fullEncryptedMessage.length) {
        println("The input image is not large enough to hold this message.")
        return
    }

    val bitArraySize = fullEncryptedMessage.length
    var count = 0
    loop@for (height in 0 until bufferedImage.height) {
        for (width in 0 until bufferedImage.width) {
            if (bitArraySize == count) break@loop

            val pixelColor = Color(bufferedImage.getRGB(width, height))
            val newRGB = Color(pixelColor.red, pixelColor.green, setLeastBit(pixelColor.blue, fullEncryptedMessage[count])).rgb
            bufferedImage.setRGB(width, height, newRGB)
            count += 1
        }
    }

    val outputFilePNG = File(outputNamePath)
    ImageIO.write(bufferedImage, "png", outputFilePNG)
    println("Message saved in $outputNamePath image.")
}

