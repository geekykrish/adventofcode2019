package aoc2019

import java.io.File

fun main(args: Array<String>) {
    val file = File(ClassLoader.getSystemResource("day1_1.txt").file).bufferedReader()
     var sum = 0
    file.readLines().forEach {
        var temp = it.toInt()
        while(temp > 6){
            temp = temp.div(3)-2
            sum += temp
        }
    }
    file.close()
    print(sum)
}
