package Day4

case class Credential(
    byr: Option[String],
    iyr: Option[String],
    eyr: Option[String],
    hgt: Option[String],
    hcl: Option[String],
    ecl: Option[String],
    pid: Option[String],
    cid: Option[String]
)

class PassportBatch(batchLines: List[String]) {
    private def takeTillBlankLine(lines: List[String]): (String, List[String]) = {
        def take(lines: List[String], taken: String): (String, List[String]) = {
            if(lines.length == 0) { return (taken, lines) }
            val line::rest = lines
            line match {
                case line if line == "" => (taken, rest)
                case _ => take(rest, taken + " " + line)
            }
        }
        take(lines, "")
    }

    private def extractBatchEntries(lines: List[String]): List[String] = {
        def extractNext(lines: List[String], entries: List[String]): List[String] = {
            if(lines.length == 0) { return entries; }
            val (entry, rest) = takeTillBlankLine(lines)
            extractNext(rest, entries:+entry)
        }
        extractNext(lines, List())
    }

    private def entryToCredentials(entry: String): Credential = {
        val regex = "([^:\\s]+):([^:\\s]+)".r
        val keyValues = regex.findAllMatchIn(entry).toList
        keyValues.foldLeft(Credential(None, None, None, None, None, None, None, None))((credential, regexMatch) => {
            regexMatch match {
                case matchedPart if matchedPart.group(1) == "byr" => credential.copy(byr=Some(matchedPart.group(2)))
                case matchedPart if matchedPart.group(1) == "iyr" => credential.copy(iyr=Some(matchedPart.group(2)))
                case matchedPart if matchedPart.group(1) == "eyr" => credential.copy(eyr=Some(matchedPart.group(2)))
                case matchedPart if matchedPart.group(1) == "hgt" => credential.copy(hgt=Some(matchedPart.group(2)))
                case matchedPart if matchedPart.group(1) == "hcl" => credential.copy(hcl=Some(matchedPart.group(2)))
                case matchedPart if matchedPart.group(1) == "ecl" => credential.copy(ecl=Some(matchedPart.group(2)))
                case matchedPart if matchedPart.group(1) == "pid" => credential.copy(pid=Some(matchedPart.group(2)))
                case matchedPart if matchedPart.group(1) == "cid" => credential.copy(cid=Some(matchedPart.group(2)))
                case _ => credential
            }
        })
    }

    val credentials = extractBatchEntries(batchLines).map(entryToCredentials)

    def credentialHasAllRequiredFields(credential: Credential): Boolean = {
        credential match {
            case Credential(Some(byr), Some(iyr), Some(eyr), Some(hgt), Some(hcl), Some(ecl), Some(pid), cid) => true
            case _ => false
        }
    }


    val birthYearIsValid = Validator.isNumericAndBetween(1920, 2002)_
    val issueYearIsValid = Validator.isNumericAndBetween(2010, 2020)_
    val expirationYearIsValid = Validator.isNumericAndBetween(2020, 2030)_
    val heightIsValid = (height: String) => {
        val regex = "^([0-9]*)(in|cm)$".r
        regex.findFirstMatchIn(height) match {
            case Some(regexMatch) if regexMatch.group(2) == "in" && regexMatch.group(1).toInt >= 59 && regexMatch.group(1).toInt <= 76 => true
            case Some(regexMatch) if regexMatch.group(2) == "cm" && regexMatch.group(1).toInt >= 150 && regexMatch.group(1).toInt <= 193 => true
            case _ => false
        }
    }
    val hairColourIsValid = Validator.matchesRegex("^#[a-f0-9]{6}$".r)_
    val eyeColourIsValid = Validator.isOneOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")_
    val isValidPassportId = Validator.matchesRegex("^[0-9]{9}$".r)_

    def credentialIsValid(credential: Credential): Boolean = {
        credential match {
            case Credential(Some(byr), Some(iyr), Some(eyr), Some(hgt), Some(hcl), Some(ecl), Some(pid), _cid) => {
                birthYearIsValid(byr) &&
                issueYearIsValid(iyr) && 
                expirationYearIsValid(eyr) && 
                heightIsValid(hgt) &&
                hairColourIsValid(hcl) &&
                eyeColourIsValid(ecl) &&
                isValidPassportId(pid)
            }
            case _ => false
        }
    }
}