package AdventOfCode

import scala.io.Source

class PuzzleInput(day: String) {
    private val inputPath = "./src/main/scala/" + day + "/input.txt"
    val rawFile = Source.fromFile(inputPath)
    val asString = () => rawFile.toString()
    val asLines = () => rawFile.getLines.toList
    val asInts = () => asLines().map(line => line.toInt)
}