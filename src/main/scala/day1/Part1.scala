package Day1 

object Part1 extends App {
  import AdventOfCode.PuzzleInput
  import Day1.TargetNumberFinder

  val input = new AdventOfCode.PuzzleInput("day1").asInts()

  val foundNumbers = TargetNumberFinder.get2NumbersThatAddTo(2020)(input)
  foundNumbers match {
    case Some((number1, number2)) => println(number1 * number2)
    case None => println("No numbers found")
  }
}
