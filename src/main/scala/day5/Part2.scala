package Day5

object Part2 extends App {
    import AdventOfCode.PuzzleInput
    val input = new AdventOfCode.PuzzleInput("day5").asLines()
    val plane = new Plane(128, 8)
    val boardingPasses = input.map(inputLine => new BoardingPass(plane, inputLine))
    val freeSeats = plane.getAvailableSeats(boardingPasses)

    def removeContigousSeats(seatIds: List[Int]): Int = {
        seatIds.take(2) match {
            case List(seatId1, seatId2) if seatId2 - seatId1 > 1 => seatId1
            case _ => removeContigousSeats(seatIds.drop(2))
        }
    }
   
    val mySeat = removeContigousSeats(freeSeats)
    println(mySeat)
}
