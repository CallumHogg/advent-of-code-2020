package Day2

case class DatabaseEntry(min: Int, max: Int, searchPattern: Char, password: String)

object DatabaseEntryDecoder {
    def decode(rawRow: String): Option[DatabaseEntry] = {
        val regex = "([0-9]*)-([0-9]*) (.): (.*$)".r
        regex.findFirstMatchIn(rawRow) match {
            case Some(regexMatch) => {
                val min = regexMatch.group(1).toInt
                val max = regexMatch.group(2).toInt
                val searchPattern = regexMatch.group(3).toCharArray().head
                val password = regexMatch.group(4)
                Some(DatabaseEntry(min, max, searchPattern, password))
            }
            case None => None
        }
    }
}
