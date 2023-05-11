package cryptography

fun cryptBiteString(string: String, password: String): String {
    var xorString = ""
    var passwordCount = 0

    for (i in string.indices) {
        if (passwordCount == password.length) {
            passwordCount = 0
        }
        xorString += if (string[i] == password[passwordCount]) "0" else "1"
        passwordCount += 1
    }

    return xorString
}

fun setLeastBit(pixelColor: Int, bit: Char): Int {
    val bitString = Integer.toBinaryString(pixelColor)
    return (bitString.substring(0, bitString.length - 1) + bit).toInt(2)
}

fun convertToBinary(message: String): String {
    var result = ""
    val binaryString = message.toCharArray()
    for(char in binaryString) {
        result += String.format("%8s", Integer.toBinaryString(char.code)).replace(' ', '0')
    }
    return result
}