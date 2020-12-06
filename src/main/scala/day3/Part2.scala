package Day3

object Part2 extends App {
    import AdventOfCode.PuzzleInput
    import Day3.TreePattern

    val input = new AdventOfCode.PuzzleInput("day3").asLines()

    val pattern = new TreePattern(input)
    val taboggan = new Taboggan(Coordinate(0, 0))
    val treeCounter = new TabogganTreeCounter(pattern, taboggan)

    val paths = List(
        Path(
            Movement(Direction.Right, 1),
            Movement(Direction.Down, 1)
        ),
        Path(
            Movement(Direction.Right, 3),
            Movement(Direction.Down, 1)
        ),
        Path(
            Movement(Direction.Right, 5),
            Movement(Direction.Down, 1)
        ),
        Path(
            Movement(Direction.Right, 7),
            Movement(Direction.Down, 1)
        ),
        Path(
            Movement(Direction.Right, 1),
            Movement(Direction.Down, 2)
        )
    )
    val answer = paths.map(path => treeCounter.findTreesOnPath(path))
                      .reduce(_ * _)
    println(answer)
}

