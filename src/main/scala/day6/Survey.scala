package Day6

case class Answer(question: Char)
case class Person(answers: List[Answer]) {
    val uniqueAnswers = answers.distinct
    val duplicateAnswers = answers.filter(uniqueAnswers.contains)
}
case class Group(people: List[Person]) {
    val answers = people.flatMap(person => person.answers)
    val uniqueAnswers = people.flatMap(person => person.uniqueAnswers).distinct
    private val firstPerson::otherPeople = people;
    val duplicateAnswers = otherPeople.foldLeft(firstPerson.duplicateAnswers)((duplicates, person) => {
        duplicates.filter(person.duplicateAnswers.contains)
    })
}

case class Survey(responses: String) {
    val groups = responses.split("\n\n").map(groupEntry => {
        val peopleEntries = groupEntry.split("\n").toList
        Group(peopleEntries.map(personEntry => {
            val answerEntries = personEntry.toCharArray()
            Person(answerEntries.map(question => Answer(question)).toList)
        }).toList)
    }).toList
}