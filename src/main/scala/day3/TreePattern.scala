package Day3

case class Coordinate(x: Int, y: Int)

class TreePattern(rows: List[String]) {
    val width = rows.head.length
    val height = rows.length

    private def getTreeCoordsFromRow(row: String, rowNumber: Int): List[Coordinate] = {
        row.toCharArray().zipWithIndex.map((charWithIndex) => {
            charWithIndex match {
                case ('#', index) => Some(Coordinate(index, rowNumber))
                case _ => None
            }
        })
        .flatten
        .toList
    }

    val treeCoords = rows.zipWithIndex.map((rowWithIndex) => {
        val (row, index) = rowWithIndex
        getTreeCoordsFromRow(row, index)
    }).flatten

    def coordinateHasTree(coord: Coordinate): Boolean = {
        val normalisedCoord = Coordinate(coord.x % width, coord.y)
        treeCoords.exists(normalisedCoord ==)
    }

    def isCoordinateOutOfBounds(coord: Coordinate): Boolean = {
        coord.y < 0 || coord.y > height
    }
}
