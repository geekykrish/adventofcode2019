import java.io.File
import java.util.*

fun main(args: Array<String>) {

    val read = Scanner(System.`in`)
    val stream = File("/Users/goku/personal/adventofcode2019/src/day1_1.txt").bufferedReader()
     var sum = 0
    stream.readLines().forEach {
        var temp = it.toInt()
        while(temp > 6){
            temp = temp.div(3)-2
            sum += temp
        }
    }
    stream.close()
    print(sum)
}
