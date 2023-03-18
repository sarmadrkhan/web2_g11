fun main() {
    // Task 1
    println("\nXX---Task 1: Matrix Rows & Columns---XX\n")
    val inputString="1 2 3\n4 5 6\n7 8 9"
    val (rows, cols) = getMatrixRowsAndColumns(inputString)

    println("Rows: $rows")
    println("Columns: $cols")

    // Task 2
    println("\nXX---Task 2: Flatten Array---XX\n")
    val inputList = listOf(1, 2, null, listOf(1, 2, 3, null), listOf(null))
    val outputList = flattenArray(inputList)

    println(outputList)

    // Task 3
    println("\nXX---Task 3: Word Count---XX\n")
    val inputPhrase = "That's the password: 'PASSWORD 123'!, cried the Special Agent.\nSo I fled."
    val counts = countWords(inputPhrase)
    for ((word, count) in counts) {
        println("$word: $count")
    }

    // Task 4
    println("\nXX---Task 4: Reactive System---XX\n")
    val system = ReactiveSystem<Int>()

    val inputCell = system.InputCell(1) //  create inputCell with initial value of 1
    val computeCell = system.ComputeCell(listOf(inputCell)) { it.first() * 2 } // create computeCell which is inputCell*2
    val subscription = computeCell.addCallback { println("New value: $it") } // create a subscription that prints out the value on change

    inputCell.value = 2 // output 4 since 2*2

    inputCell.value = 3 // output 6 since 3*2

    subscription.cancel() // subscription cancelled so no more printing after next change as callback is not executed

    inputCell.value = 5

}