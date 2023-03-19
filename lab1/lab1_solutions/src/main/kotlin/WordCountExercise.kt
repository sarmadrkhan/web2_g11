fun countWords(phrase: String): Map<String, Int> {
    // Split the phrase into words using regex
    val words = phrase.toLowerCase().split(Regex("[^\\w']+"))

    // Count the occurrences of each word using a map
    val counts = mutableMapOf<String, Int>()
    for (word in words) {
        if (word.isNotEmpty()) {
            var cleanedWord = word
            if (cleanedWord.startsWith("'")) {
                cleanedWord = cleanedWord.substring(1)
            }
            if (cleanedWord.endsWith("'")) {
                cleanedWord = cleanedWord.substring(0, cleanedWord.length - 1)
            }
            counts[cleanedWord] = counts.getOrDefault(cleanedWord, 0) + 1
        }
    }
    return counts
}