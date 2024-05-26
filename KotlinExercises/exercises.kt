import kotlin.math.PI

/**
 * For displaying the output of exercises in terminal
 *
 * To compile the program, run the following command in the terminal:
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
    zad4()
    zad5()
    zad6()
    zad7()
    zad8()
    zad9()
    zad10()
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
    val greenNumbersCount = greenNumbers.count();
    val redNumbersCount = redNumbers.count();
    val totalNumbers = greenNumbersCount + redNumbersCount;
    println("\nExercise 3")
    println("Green count: $greenNumbersCount")
    println("Red count: $redNumbersCount")
    println("Total count: $totalNumbers")
}

/**
 * Exercise 4
 * You have a set of protocols supported by your server.
 * A user requests to use a particular protocol.
 * Complete the program to check whether the requested protocol is supported or not (isSupported must be a Boolean value).
 *
 * fun main() {
 *  val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
 *  val requested = "smtp"
 *  val isSupported = // Write your code here
 *  println("Support for $requested: $isSupported")
 * }
 */
fun zad4() {
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED
    println("\nExercise 4")
    println("SUPPORTED: $SUPPORTED")
    println("Requested: $requested")
    println("Support for $requested: $isSupported")
}


/**
 * Exercise 5
 * Define a map that relates integer numbers from 1 to 3 to their corresponding spelling. Use this map to spell the given number.
 *
 * fun main() {
 *  val number2word = // Write your code here
 *  val n = 2
 *  println("$n is spelt as '${<Write your code here >}'")
 * }
 */
fun zad5() {
    val number2word = mapOf(1 to "jeden", 2 to "dwa", 3 to "trzy")
    val n = 2
    println("\nExercise 5")
    println("number2word: $number2word")
    println("$n is spelt as ${number2word[n]}")
}

/**
 * Exercise 6
 * Using a when expression, update the following program so that when you input the names of GameBoy buttons, the actions are printed to output.
 *
 * fun main() {
 *  val button = "A"
 *  println(
 *      // Write your code here
 *  )
 * }
 */
fun zad6() {
    val button = "A"

    fun controller(button: String): String {
        return when (button) {
            "A" -> "Yes"
            "B" -> "No"
            "X" -> "Menu"
            "Y" -> "Nothing"
            else -> "There is no such button"
        }
    }
    println("\nExercise 6")
    println("Button $button")
    println("Output: ${controller(button)}")
}

/**
 * Exercise 7
 * You have a program that counts pizza slices until there’s a whole pizza with 8 slices. Refactor this program in two ways:
 * - Use a while loop.
 * - Use a do-while loop.
 *
 * fun main() {
 *  var pizzaSlices = 0
 *  // Start refactoring here
 *  pizzaSlices++
 *  println("There's only $pizzaSlices slice/s of pizza :(")
 *  pizzaSlices++
 *  println("There's only $pizzaSlices slice/s of pizza :(")
 *  pizzaSlices++
 *  println("There's only $pizzaSlices slice/s of pizza :(")
 *  pizzaSlices++
 *  println("There's only $pizzaSlices slice/s of pizza :(")
 *  pizzaSlices++
 *  println("There's only $pizzaSlices slice/s of pizza :(")
 *  pizzaSlices++
 *  println("There's only $pizzaSlices slice/s of pizza :(")
 *  pizzaSlices++
 *  println("There's only $pizzaSlices slice/s of pizza :(")
 *  pizzaSlices++
 *  // End refactoring here
 *  println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
 * }
 */
fun zad7() {
    fun whileLoop() {
        var pizzaSlices = 0
        while (pizzaSlices < 8) {
            println("There's only $pizzaSlices slice/s of pizza :(")
            pizzaSlices++
        }
        println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
    }

    fun doWhileLoop() {
        var pizzaSlices = 0
        do {
            println("There's only $pizzaSlices slice/s of pizza :(")
            pizzaSlices++
        } while (pizzaSlices < 8)
        println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
    }
    println("\nExercise 7")
    println("While loop:")
    whileLoop()
    println("\nDo-while loop:")
    doWhileLoop()
}


/**
 * Exercise 8
 * Write a program that simulates the Fizz buzz game. Your task is to print numbers from 1 to 100 incrementally,
 * replacing any number divisible by three with the word "fizz", and any number divisible by five with the word "buzz".
 * Any number divisible by both 3 and 5 must be replaced with the word "fizzbuzz".
 */
fun zad8() {
    println("\nExercise 8")
    for (i in 1..100) {
        when {
            i % 3 == 0 && i % 5 == 0 -> println("fizzbuzz")
            i % 3 == 0 -> println("fizz")
            i % 5 == 0 -> println("buzz")
            else -> println(i)
        }
    }
}


/**
 * Exercise 9
 * You have a list of words. Use for and if to print only the words that start with the letter l.
 */
fun zad9() {
    println("\nExercise 9")
    val words = listOf("dinosaur", "limousine", "magazine", "language")

    for (word in words) {
        if (word.startsWith("l")) {
            println(word)
        }
    }
}


/**
 * Exercise 10
 * Write a function called circleArea that takes the radius of a circle in integer format as a parameter and outputs the area of that circle.
 */
fun zad10() {
    val circleRadius = 2
    fun circleArea(radius: Int): Double {
        return PI * radius * radius
    }

    var output = circleArea(circleRadius)
    println("\nExercise 10")
    println("Circle area: ${output}")
}