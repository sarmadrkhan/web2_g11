fun getMatrixRowsAndColumns(inputString: String): Pair<List<List<Int>>, List<List<Int>>> {
    val rows = inputString.trim().split("\n").map { row ->
        row.trim().split(" ").map {
            it.toInt()
        }
    }

    val numColumns = rows[0].size // all have same size so taking the size of 1 row

    val columns = Array(numColumns) { mutableListOf<Int>() } // initialize empty array for each col

    for (row in rows) { // for each row
        for (i in 0 until numColumns) { // get values for each of the 3 cols
            columns[i].add(row[i])
        }
    }
    val updatedColumns = columns.map { column -> column.toList() }

    return Pair(rows, updatedColumns)
}