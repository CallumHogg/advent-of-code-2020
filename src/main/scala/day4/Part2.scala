package Day4

object Part2 extends App {
    import AdventOfCode.PuzzleInput
    val input = new AdventOfCode.PuzzleInput("day4").asLines()
    val batch = new PassportBatch(input)

    val validCredentials = batch.credentials.filter(batch.credentialIsValid)
    println(validCredentials.length)
}

