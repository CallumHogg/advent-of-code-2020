package Day3

object Direction extends Enumeration {
  val Up, Down, Left, Right = Value
}

case class Movement(direction: Direction.Value, distance: Int)

case class Path(movements: Movement*)

class Taboggan(val position: Coordinate) {
    def move(movement: Movement): Taboggan = {
        val newPosition = movement match {
            case Movement(Direction.Up, distance) => Coordinate(position.x, position.y + (distance * -1))
            case Movement(Direction.Down, distance) => Coordinate(position.x, position.y + distance)
            case Movement(Direction.Left, distance) => Coordinate(position.x + (distance * -1), position.y)
            case Movement(Direction.Right, distance) => Coordinate(position.x + distance, position.y)
            case _ => position
        }
        new Taboggan(newPosition)
    }

    def followPath(path: Path): Taboggan = {
        path.movements.headOption match {
            case None => this
            case Some(movement) => move(movement).followPath(Path(path.movements.tail:_*))
        }
    }
}
