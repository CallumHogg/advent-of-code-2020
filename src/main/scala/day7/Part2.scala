package Day7

import scala.collection.immutable.HashMap

object Part2 extends App {
    import AdventOfCode.PuzzleInput
    val input = new AdventOfCode.PuzzleInput("day7").asLines()
    val bagRuleSet = new BagRuleSet(input)
    bagRuleSet.getBag("shiny gold") match {
        case Some(bag) => println(bagRuleSet.getTotalChildBags(bag))
        case None => println("Bag not found in rule set")
    }
}
