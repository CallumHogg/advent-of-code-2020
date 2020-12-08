package Day6

object Part2 extends App {
    import AdventOfCode.PuzzleInput
    val input = new AdventOfCode.PuzzleInput("day6").asString()
    val survey = Survey(input)
    val uniqueGroupCounts = survey.groups
                                .map(group => group.duplicateAnswers.length)
                                .reduce(_+_)
    println(uniqueGroupCounts)
}
