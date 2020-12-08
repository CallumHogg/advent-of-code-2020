package Day6

object Part1 extends App {
    import AdventOfCode.PuzzleInput
    val input = new AdventOfCode.PuzzleInput("day6").asString()
    val survey = Survey(input)
    val uniqueGroupCounts = survey.groups
                                .map(group => group.uniqueAnswers.length)
                                .reduce(_+_)
    println(uniqueGroupCounts)
}
