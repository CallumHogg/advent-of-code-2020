package Day7

import scala.collection.immutable.HashMap

case class Rule(number: Int, colour: String)
case class Bag(colour: String, rules: List[Rule])

class BagRuleSet(rawRules: List[String]) {
    private def parseRawRule(ruleMap: HashMap[String, Bag], input: String): HashMap[String, Bag] = {
        val split1 = input.split(" bags contain ")
        val colour = split1.head
        val rules = split1.tail.foldLeft("")(_ + _).dropRight(1).split(", ").filter(rule => rule != "no other bags").map(rule => {
            val split2 = rule.split(" ")
            val ruleNumber = split2.head
            val ruleColour = split2.tail.mkString(" ").split(" bag").head
            Rule(ruleNumber.toInt, ruleColour)
        }).toList
        ruleMap + ((colour, Bag(colour, rules)))
    }

    private def parseRawRules(rawRules: List[String]): HashMap[String, Bag] = {
        rawRules.foldLeft(new HashMap[String, Bag]())(parseRawRule)
    }

    val ruleMap = parseRawRules(rawRules)
    val bags = ruleMap.values.toList
 
    def getBag(bagColour: String): Option[Bag] = {
        ruleMap.get(bagColour)
    }
    
    def getBagsThatDirectlyContain(checkBag: Bag): List[Bag] = {
        ruleMap.values.toList.filter(bag => {
            bag.rules.exists(rule => {
                rule.colour == checkBag.colour
            })
        })
    }

    def getTopLevelBagsContaining(checkBag: Bag): List[Bag] = {
        def recurseGetTopLevelBagsContaining(checkBag: Bag): List[Bag] = {
            getBagsThatDirectlyContain(checkBag) match {
                case containingBags if containingBags.length == 0 => List()
                case containingBags => containingBags ++ containingBags.flatMap(bag => recurseGetTopLevelBagsContaining(bag))
            }
        }
        recurseGetTopLevelBagsContaining(checkBag).distinct
    }

    def getTotalChildBags(checkBag: Bag): Int = {
        def recurseGetTotalChildBags(checkBagColour: String): Int = {
            val bag = ruleMap.get(checkBagColour)
            bag match {
                case Some(bag) if bag.rules.length > 0 => 1 + bag.rules.map(rule => rule.number * recurseGetTotalChildBags(rule.colour)).sum
                case Some(bag) if bag.rules.length == 0 => 1
                case None => 0
            }
        }
        recurseGetTotalChildBags(checkBag.colour) - 1
    }
}