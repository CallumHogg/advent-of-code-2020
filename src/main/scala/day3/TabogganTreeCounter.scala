package Day3

class TabogganTreeCounter(pattern: TreePattern, taboggan: Taboggan) {
    def findTreesOnPath(path: Path): Int = {
        def followPathAndCountTrees(taboggan: Taboggan, treesEncountered: Int=0): Int = {
            taboggan.position match {
                case position if pattern.isCoordinateOutOfBounds(position) => treesEncountered
                case position => {
                    val newTaboggan = taboggan.followPath(path)
                    if(pattern.coordinateHasTree(newTaboggan.position)) {
                        return followPathAndCountTrees(newTaboggan, treesEncountered + 1)
                    }
                    followPathAndCountTrees(newTaboggan, treesEncountered)
                }
            }
        }
        followPathAndCountTrees(taboggan)
    }
}