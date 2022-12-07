package aoc2022

fun main() {
    val input = readLines("input/day7.txt")

    val totalSpace = 70000000
    val updateSize = 30000000
    val root = Folder(null, "/")
    var cursor = Folder(null, "")
    for (terminalLine in input) {
        when {
            terminalLine == "$ cd /" -> { cursor = root }
            terminalLine == "$ cd .." -> cursor = cursor.parent!!
            terminalLine.startsWith("$ cd") -> cursor = cursor.getChildFolder(terminalLine.substring(5))
            terminalLine.startsWith("$ ls") -> continue
            terminalLine.startsWith("dir ") -> cursor.add(Folder(cursor, terminalLine.substring(4)))
            else -> {
                val terminalLineParts = terminalLine.split(" ")
                cursor.add(File(terminalLineParts[1], terminalLineParts[0].toInt()))
            }
        }
    }

    val allFolders = mutableListOf<Folder>()
    allFolders.add(root)
    allFolders.addAll(root.getAllSubfolders())
    println(allFolders.filter { it.size() < 100000 }.sumOf { it.size() })

    val availableSpace = totalSpace - root.size()
    val spaceToFree = updateSize - availableSpace
    println(allFolders.filter { it.size() >= spaceToFree }.minOf { it.size() })
}

abstract class Folderable(val name: String) {
    abstract fun size(): Int
}

class Folder(val parent: Folder?, name: String): Folderable(name) {
    private val folderables = mutableListOf<Folderable>()
    private val folders = mutableMapOf<String, Folder>()
    override fun size() = folderables.sumOf { it.size() }

    fun add(folderable: Folderable) {
        folderables.add(folderable)
        if (folderable is Folder) {
            folders[folderable.name] = folderable
        }
    }

    fun getChildFolder(name: String): Folder {
        return folders.getValue(name)
    }

    fun getAllSubfolders(): List<Folder> {
        val allFolders = mutableListOf<Folder>()
        allFolders.addAll(folders.values)
        allFolders.addAll(folders.values.flatMap { it.getAllSubfolders() })
        return allFolders
    }

    override fun toString(): String {
        return "Folder -> name: $name, contents: $folderables"
    }
}

class File(name: String, private val size: Int): Folderable(name) {
    override fun size() = size

    override fun toString(): String {
        return "File -> name: $name, size: $size"
    }
}