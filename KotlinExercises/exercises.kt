/**
 * For displaying the output of exercises in terminal
 * 
 * To compile the program, run the following command:
 * 
 * kotlinc exercises.kt -include-runtime -d exercises.jar
 * java -jar exercises.jar
 * 
 * You need to install Kotlin and Java 17 to run this program
 * On Ubuntu, you can install Kotlin using Homebrew:
 * brew install kotlin
 * 
 * You could also install Java using Homebrew.
 * 
 * @autor:    Łukasz Kos, s22051
 */
fun main() {
    zad1()
    zad2()
    zad3()
}

/**
 * Exercise 1
 * Complete the code to make the program print "Mary is 20 years old" to standard output:
 * 
 * fun main() {
 *  val name = "Mary"
 *  val age = 20
 *  // Write your code here
 * }
 */
fun zad1() {
    println("\nExercise 1")
    val name = "Mary"
    val age = 20
    println("$name is $age years old")
}

/**
 * Exercise 2
 * Explicitly declare the correct type for each variable:
 */
fun zad2() {
    val a: Short = 1000
    val b: String = "log message"
    val c: Double = 3.14
    val d: Long = 100_000_000_000_000
    val e: Boolean = false
    val f: Char = '\n'
    println("\nExercise 2")
    println("a: ${a::class.simpleName}")
    println("b: ${b::class.simpleName}")
    println("c: ${c::class.simpleName}")
    println("d: ${d::class.simpleName}")
    println("e: ${e::class.simpleName}")
    println("f: ${f::class.simpleName}")
}

/**
 * TODO: str. 71
 * Exercise 3
 * You have a list of “green” numbers and a list of “red” numbers. Complete the code to print how many numbers there are in total.
 * 
 * fun main() {
 *  val greenNumbers = listOf(1, 4, 23)
 *  val redNumbers = listOf(17, 2)
 *  // Write your code here
 * }
 */

 fun zad3() {
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    // Write your code here
}