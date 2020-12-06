package Day3

object Part1 extends App {
    import AdventOfCode.PuzzleInput
    import Day3.TreePattern

    val input = new AdventOfCode.PuzzleInput("day3").asLines()

    val pattern = new TreePattern(input)
    val taboggan = new Taboggan(Coordinate(0, 0))
    val treeCounter = new TabogganTreeCounter(pattern, taboggan)

    val path = Path(
        Movement(Direction.Right, 3),
        Movement(Direction.Down, 1)
    )
    val answer = treeCounter.findTreesOnPath(path)
    println(answer)
}
