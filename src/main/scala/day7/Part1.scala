package Day7

object Part1 extends App {
    import AdventOfCode.PuzzleInput
    val input = new AdventOfCode.PuzzleInput("day7").asLines()
    val bagRuleSet = new BagRuleSet(input)
    bagRuleSet.getBag("shiny gold") match {
        case Some(bag) => println(bagRuleSet.getTopLevelBagsContaining(bag).length)
        case None => println("Bag not found in rule set")
    }
}
