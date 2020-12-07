package Day4

import scala.util.matching.Regex

object Validator {
    def matchesRegex(regex: Regex)(test: String): Boolean = {
        regex.findFirstIn(test) match {
            case None => false
            case _ => true
        }
    }

    def isExactly(exact: String)(test: String): Boolean = exact == test
    def startsWith(start: String)(test: String): Boolean = isExactly(start)(test.slice(0, start.length - 1))
    def endsWith(end: String)(test: String): Boolean = startsWith(end.reverse)(test.reverse)

    def isNumericAndBetween(min: Int, max: Int)(test: String): Boolean = {
        matchesRegex("^\\d*$".r)(test) && test.toInt >= min && test.toInt <= max
    }

    def or(predicates: String => Boolean*)(test: String): Boolean = {
        predicates.exists(predicate => predicate(test) == true)
    }

    def and(predicates: String => Boolean*)(test: String): Boolean = {
        predicates.forall((predicate => predicate(test) == true))
    }

    def isOneOf(matches: String*)(test: String): Boolean = or(matches.map(isExactly):_*)(test)
}