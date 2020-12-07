package Day5

object Part1 extends App {
    import AdventOfCode.PuzzleInput
    val input = new AdventOfCode.PuzzleInput("day5").asLines()
    val plane = new Plane(128, 8)
    val boardingPasses = input.map(inputLine => new BoardingPass(plane, inputLine))
    val biggestSeatId = boardingPasses.sortBy(boardingPass => -boardingPass.seatId).head.seatId
    println(biggestSeatId)
}

