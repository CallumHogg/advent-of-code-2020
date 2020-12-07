package Day5

class Plane(val rows: Int, val columns: Int) {
    def getSeatId(row: Int, column: Int): Int = (row * 8) + column
    private def getSeatIdsInRow(row: Int, columns: Int): List[Int] = Range(0, columns).toList.map(column => getSeatId(row, column))
    private def getSeatIds(rows: Int, columns: Int): List[Int] = Range(0, rows).toList.flatMap(row => getSeatIdsInRow(row, columns))

    val seatIds = getSeatIds(rows, columns)

    def getAvailableSeats(boardingPasses: List[BoardingPass]): List[Int] = {
        seatIds.filterNot(seatId => boardingPasses.exists(boardingPass => boardingPass.seatId == seatId))
    }
}