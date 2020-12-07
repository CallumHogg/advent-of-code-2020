package Day5

class BoardingPass(plane: Plane, seatCode: String) {
    val (rowPart, columnPart) = seatCode.splitAt(7)

    def decodeBoardingPassPart(maximum: Int, lowerHalfCode: Char, upperHalfCode: Char)(fullCode: String): Int = {
        def decodeFullCode(lowerBound: Int, upperBound: Int, code: String): Int = {
            val diff = (upperBound - lowerBound)
            val divided = (diff / 2)
            code.headOption match {
                case Some(codeChar) if codeChar == lowerHalfCode => decodeFullCode(lowerBound, upperBound - divided, code.tail)
                case Some(codeChar) if codeChar == upperHalfCode => decodeFullCode(lowerBound + divided, upperBound, code.tail)
                case Some(codeChar) => throw new Error("Invalid code " + code)
                case None => lowerBound
            }
        }
        decodeFullCode(0, maximum, fullCode)
    }

    val rowNumber = decodeBoardingPassPart(plane.rows, 'F', 'B')(rowPart)
    val columnNumber = decodeBoardingPassPart(plane.columns, 'L', 'R')(columnPart)
    val seatId = plane.getSeatId(rowNumber, columnNumber)
}
