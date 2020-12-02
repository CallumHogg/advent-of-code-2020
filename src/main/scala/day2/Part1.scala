package Day2

object Part1 extends App {
    import AdventOfCode.PuzzleInput
    import Day2.DatabaseEntryDecoder
    import Day2.PasswordChecker

    val input = new AdventOfCode.PuzzleInput("day2").asLines()
    val databaseEntries = input.map(line => DatabaseEntryDecoder.decode(line))

    val validator = (entry: DatabaseEntry) => PasswordChecker.hasCorrectNumberOfChars(entry.min, entry.max, entry.searchPattern)(entry.password)
    val validEntries = databaseEntries.filter((entry) => {
        entry match {
            case Some(entry) => validator(entry)
            case None => false
        }
    })

    println(validEntries.length)
}
