package Day2

object PasswordChecker {
    private def getCharacterPositionsInString(char: Char, string: String): List[Int] = {
        string.toCharArray().zipWithIndex
              .filter((charWithIndex) => charWithIndex._1 == char)
              .map(charWithIndex => charWithIndex._2 + 1)
              .toList
    }

    def hasCorrectNumberOfChars(min: Int, max: Int, char: Char)(password: String): Boolean = {
        val characterPositions = getCharacterPositionsInString(char, password)
        characterPositions.length >= min && characterPositions.length <= max
    }

    def characterAppearsInPosition(expectedPosition: Int, char: Char)(password: String): Boolean = {
        val characterPositions = getCharacterPositionsInString(char, password)
        characterPositions.exists((position) => position == expectedPosition)
    }

    def characterAppearsInOnlyOneOfPositions(positions: List[Int], char: Char)(password: String): Boolean = {
        val result = positions.exists((expectedPosition) => characterAppearsInPosition(expectedPosition, char)(password))
        val matchedPositions = positions.filter((expectedPosition) => characterAppearsInPosition(expectedPosition, char)(password))
        matchedPositions.length == 1
    }
}
