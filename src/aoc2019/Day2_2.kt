package aoc2019

import java.io.File


fun main(args: Array<String>) {
    val file = File(ClassLoader.getSystemResource("day2_2.txt").file).bufferedReader()
    var intcodes: IntArray
    var splits = file.readLine().split(",")
    for(k in 0..99) {
        for(j in 0..99) {
            intcodes = splits.map { it.toInt() }.toIntArray()
            intcodes[1] = k
            intcodes[2] = j
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
            println("::"+k+"::"+j)
            println(intcodes[0])
            if(intcodes[0] == 19690720) {
                println("**************************************************************************")
                println("::"+k+"::"+j)
            }

        }
    }


}
