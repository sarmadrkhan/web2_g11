fun flattenArray(inputArray: List<Any?>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in inputArray) {
        when (element) {
            is List<*> -> result.addAll(flattenArray(element.filterNotNull()))
            is Int -> result.add(element)
        }
    }
    return result
}