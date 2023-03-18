fun countWords(phrase: String): Map<String, Int> {
    // Split the phrase into words using regex
    val words = phrase.toLowerCase().split(Regex("[^\\w']+"))

    // Count the occurrences of each word using a map
    val counts = mutableMapOf<String, Int>()
    for (word in words) {
        if (word.isNotEmpty()) {
            counts[word] = counts.getOrDefault(word, 0) + 1
        }
    }
    return counts
}