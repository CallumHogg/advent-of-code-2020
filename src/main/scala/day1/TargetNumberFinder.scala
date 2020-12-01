package Day1

object TargetNumberFinder {
    private def get2SortedNumbersThatAddTo(target: Int)(sortedNumbers: List[Int]): Option[(Int, Int)] = {
        if (sortedNumbers.length < 2) { return None }
        val smallest = sortedNumbers.head
        val biggest = sortedNumbers.last
        val total = smallest + biggest
        total match {
            case total if total > target => get2SortedNumbersThatAddTo(target)(sortedNumbers.dropRight(1))
            case total if total < target => get2SortedNumbersThatAddTo(target)(sortedNumbers.drop(1))
            case _ =>  Some(smallest, biggest)
        }
    }

    def get2NumbersThatAddTo(target: Int)(numbers: List[Int]): Option[(Int, Int)] = get2SortedNumbersThatAddTo(target)(numbers.sorted)

    private def get3SortedNumbersThatAddTo(target: Int)(sortedNumbers: List[Int]): Option[(Int, Int, Int)] = {
        if (sortedNumbers.length < 3) { return None }
        val smallest = sortedNumbers.head
        val rest = sortedNumbers.tail
        val diff = target - smallest
        val foundNumbers = get2SortedNumbersThatAddTo(diff)(rest)
        foundNumbers match {
            case Some((number1, number2)) => Some(smallest, number1, number2)
            case None => get3SortedNumbersThatAddTo(target)(rest)
        }
    }

    def get3NumbersThatAddTo(target: Int)(numbers: List[Int]): Option[(Int, Int, Int)] = get3SortedNumbersThatAddTo(target)(numbers.sorted)
}
