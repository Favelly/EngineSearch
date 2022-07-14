class PeopleView {

    enum class Option {
        EXIT,
        FIND,
        SHOW
    }

    fun getPeople(): List<String> {
        println("Enter the number of people:")
        val number = readln().toInt()
        println("Enter all people:")
        return (1..number).map { readln() }
    }

    fun getMenuOption(): Option? {
        println()
        println("""
            === Menu ===
            1. Find a person
            2. Print all people
            0. Exit
        """.trimIndent()
        )
        return getOption(readln().toInt())
    }

    private fun getOption(index: Int) =
        if (index in Option.values().indices) {
            Option.values()[index]
        } else {
            null
        }

    fun getQuery(): Pair<String, Strategy> {
        val strategy = getStrategy()
        println()
        println("Enter a name or email to search all suitable people.")
        return readln() to strategy
    }

    private fun getStrategy(): Strategy {
        println()
        println("Select a matching strategy: ${Strategy.values().joinToString()}")
        val strategy = readln().uppercase()
        return try {
            Strategy.valueOf(strategy)
        } catch (e: IllegalArgumentException) {
            println("Incorrect strategy! Try again.")
            getStrategy()
        }
    }

    fun showIncorrectMessage() {
        println()
        println("Incorrect option! Try again.")
    }

    fun showFoundPeople(peopleFound: List<String>) {
        if (peopleFound.isEmpty()) {
            println("No matching people found.")
        } else {
            val many = peopleFound.size > 1
            println("${peopleFound.size} person${if (many) "s" else ""} found:")
            peopleFound.forEach(::println)
        }
    }

    fun showAllPeople(allPeople: List<String>) {
        println()
        println("=== List of people ===")
        allPeople.forEach(::println)
    }

    fun showExitMessage() {
        println()
        println("Bye!")
    }
}