import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.math.abs


typealias Point = Pair<Int, Int>

val Point.x: Int get() = first
val Point.y: Int get() = second

object Day3_1 {

    data class Instruction(val direction: String, val count: Int)

    data class Wire(val lastLoc: Point, val locations: LinkedList<Point>)

    private fun String.makeInstruction(): Instruction = Instruction(take(1), drop(1).toInt())

    private const val FILENAME = "src/inputfiles/day03.txt"

    private fun String.processLine(): List<Instruction> = split(",").map { it.makeInstruction() }

    private fun List<String>.toWires(): List<Wire> {
        return map { line ->
            line.processLine()
                    .fold(Wire(0 to 0, LinkedList())) { wire, instr ->
                        val newLocs = when (instr.direction) {
                            "U" -> (wire.lastLoc.y + 1..wire.lastLoc.y + instr.count).map { (wire.lastLoc.x to it) }
                            "D" -> (wire.lastLoc.y - instr.count until wire.lastLoc.y).reversed().map { (wire.lastLoc.x to it) }
                            "R" -> (wire.lastLoc.x + 1..wire.lastLoc.x + instr.count).map { (it to wire.lastLoc.y) }
                            "L" -> (wire.lastLoc.x - instr.count until wire.lastLoc.x).reversed().map { (it to wire.lastLoc.y) }
                            else -> throw Error("bad instruction $instr")
                        }
                        wire.locations.addAll(newLocs)
                        Wire(newLocs.last(), wire.locations)
                    }
        }
    }

    private val wires =
            Files.readAllLines(Paths.get(FILENAME)).toWires().map { it.locations }

    private val wiresAsSet = wires.map { it.toSet() }


    private fun List<Set<Point>>.overlaps(): Set<Point> {
        return this[0].intersect(this[1].toSet())
    }

    private fun Set<Point>.calculateShortest(wires0: LinkedList<Point>, wires1: LinkedList<Point>): String {
        return map { match ->
            wires0.takeWhile { p -> p != match } to wires1.takeWhile { p -> p != match }
        }.minBy { it.first.size + it.second.size }!!
                .let {
                    it.first.size + it.second.size + 2
                }.toString()
    }

    fun part1() =
            wiresAsSet.overlaps().map { abs(it.x.toDouble()) + abs(it.y.toDouble()) }.min()!!.toInt().toString()

    fun part2() = wiresAsSet.overlaps()
            .calculateShortest(wires[0], wires[1])

}


fun main() {
    println("Part 1: ${Day3_1.part1()}")
    println("Part 2: ${Day3_1.part2()}")
}