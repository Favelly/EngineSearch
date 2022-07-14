class SearchEngine(var lines: List<String> = emptyList()) {
    private val invertedIndex
        get() = lines
            .asSequence()
            .map { it.split(' ') }
            .flatten()
            .map { it.lowercase() }
            .groupBy { it }
            .map {
                it.key to lines.indices.filter { i ->
                    lines[i].lowercase().contains(it.key)
                }
            }
            .toList().toMap()

    fun find(query: String, strategy: Strategy) =
        when (strategy) {
            Strategy.ALL -> findAll(query)
            Strategy.ANY -> findAny(query)
            Strategy.NONE -> findNone(query)
        }

    private fun findAll(query: String): List<String> {
        val every = findForEvery(query)
        return every
            .flatten()
            .toSet()
            .filter { line ->
                every.all { it.contains(line) }
            }
    }

    private fun findAny(query: String) =
        findForEvery(query)
            .flatten()
            .toSet()
            .toList()

    private fun findNone(query: String) =
        lines - findForEvery(query).flatten().toSet()

    private fun findForEvery(query: String) = query
        .split(' ')
        .map(::findWord)

    private fun findWord(word: String) =
        invertedIndex[word]?.map { lines[it] }
            ?: emptyList()
}