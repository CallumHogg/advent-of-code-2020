package Day1 

object Part2 extends App {
  import AdventOfCode.PuzzleInput
  import Day1.TargetNumberFinder

  val input = new AdventOfCode.PuzzleInput("day1").asInts()

  val foundNumbers = TargetNumberFinder.get3NumbersThatAddTo(2020)(input)
  foundNumbers match {
    case Some((number1, number2, number3)) => println(number1 * number2 * number3)
    case None => println("No numbers found")
  }
}