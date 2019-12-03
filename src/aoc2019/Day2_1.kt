package aoc2019

import java.io.File


fun main(args: Array<String>) {
    val file = File(ClassLoader.getSystemResource("day2_1.txt").file).bufferedReader()
    var intcodes: IntArray
    intcodes = file.readLine().split(",").map { it.toInt() }.toIntArray()
    intcodes.iterator().forEach { println(it) }
   var i= 0
    while(i < intcodes.size) {
        when(intcodes[i]) {
            1 -> {
                intcodes[intcodes[i+3]] = intcodes[intcodes[i+1]] + intcodes[intcodes[i+2]]
                i +=4

            }
            2 -> {
                intcodes[intcodes[i+3]] = intcodes[intcodes[i+1]] * intcodes[intcodes[i+2]]
                i +=4
            }
            99 -> {
                i = intcodes.size
            }
            else -> {
                i +=1
            }
        }
    }
    println("**************************************************************************")
    intcodes.iterator().forEach { println(it) }
}
