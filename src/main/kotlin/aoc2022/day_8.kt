package aoc2022

fun main() {
    val input = readLines("input/day8.txt")

    // setup forest
    val forest = mutableListOf<List<Tree>>()
    for (treeRow in input) {
        val forestRow = mutableListOf<Tree>()
        forest.add(forestRow)
        for(tree in treeRow) {
            forestRow.add(Tree(tree.digitToInt()))
        }
    }

    val sideLength = forest.size

    // check from west
    for(row in 0 until sideLength) {
        var highHeight: Int = -1
        for(col in 0 until sideLength) {
            val tree = forest[row][col]
            if(tree.height > highHeight) {
                tree.visibleFromWest = true
                highHeight = tree.height
            }
        }
    }

    // check from east
    for(row in 0 until sideLength) {
        var highHeight: Int = -1
        for(col in sideLength-1 downTo 0) {
            val tree = forest[row][col]
            if(tree.height > highHeight) {
                tree.visibleFromEast = true
                highHeight = tree.height
            }
        }
    }

    // check from north
    for(col in 0 until sideLength) {
        var highHeight: Int = -1
        for(row in 0 until sideLength) {
            val tree = forest[row][col]
            if(tree.height > highHeight) {
                tree.visibleFromNorth = true
                highHeight = tree.height
            }
        }
    }

    // check from south
    for(col in 0 until sideLength) {
        var highHeight: Int = -1
        for(row in sideLength-1 downTo 0) {
            val tree = forest[row][col]
            if(tree.height > highHeight) {
                tree.visibleFromSouth = true
                highHeight = tree.height
            }
        }
    }

    // part 2
    for(row in 0 until sideLength) {
        for(col in 0 until sideLength) {
            val tree = forest[row][col]
            val treeHeight: Int = tree.height

            // check north
            var movement = 1
            while (row - movement >= 0) {
                movement++
                if (forest[row - (movement-1)][col].height >= treeHeight) {
                    break
                }
            }
            tree.visibilityToNorth = movement - 1

            // check south
            movement = 1
            while (row + movement <= sideLength - 1) {
                movement++
                if(forest[row + (movement-1)][col].height >= treeHeight) {
                    break
                }
            }
            tree.visibilityToSouth = movement - 1

            // check west
            movement = 1
            while (col - movement >= 0) {
                movement++
                if (forest[row][col - (movement-1)].height >= treeHeight) {
                    break
                }
            }
            tree.visibilityToWest = movement - 1

            // check west
            movement = 1
            while (col + movement <= sideLength - 1) {
                movement++
                if (forest[row][col + (movement-1)].height >= treeHeight) {
                    break
                }
            }
            tree.visibilityToEast = movement - 1
        }
    }

    println(forest.sumOf { it.count { tree -> tree.isVisible() } })
    println(forest.maxOf { it.maxOf { tree -> tree.scenicScore() } })
}

class Tree(val height: Int) {
    var visibleFromNorth: Boolean = false
    var visibleFromSouth: Boolean = false
    var visibleFromWest: Boolean = false
    var visibleFromEast: Boolean = false
    var visibilityToNorth: Int = 0
    var visibilityToEast: Int = 0
    var visibilityToSouth: Int = 0
    var visibilityToWest: Int = 0

    fun isVisible(): Boolean = visibleFromNorth or visibleFromEast or visibleFromSouth or visibleFromWest

    fun scenicScore(): Int = visibilityToNorth * visibilityToEast * visibilityToSouth * visibilityToWest

    override fun toString(): String {
        return "Tree[height=$height;N=$visibilityToNorth;E=$visibilityToEast;S=$visibilityToSouth;W=$visibilityToWest]"
    }
}