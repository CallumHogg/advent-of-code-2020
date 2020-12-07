package Day4

object Part1 extends App {
    import AdventOfCode.PuzzleInput
    val input = new AdventOfCode.PuzzleInput("day4").asLines()
    val batch = new PassportBatch(input)

    val validCredentials = batch.credentials.filter(batch.credentialHasAllRequiredFields)
    println(validCredentials.length)
}

