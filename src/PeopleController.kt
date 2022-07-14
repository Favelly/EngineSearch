import java.io.File

class PeopleController(private val data: File? = null) {
    private val searchEngine = SearchEngine()
    private val peopleView = PeopleView()

    fun launch() {
        loadPeople()
        handleMenu()
    }

    private fun loadPeople() {
        searchEngine.lines = data?.readLines()
            ?: peopleView.getPeople()
    }

    private fun handleMenu() {
        while (true) {
            when (peopleView.getMenuOption()) {
                PeopleView.Option.FIND ->
                    findPerson()
                PeopleView.Option.SHOW ->
                    peopleView.showAllPeople(searchEngine.lines)
                PeopleView.Option.EXIT -> {
                    peopleView.showExitMessage()
                    break
                }
                null -> peopleView.showIncorrectMessage()
            }
        }
    }

    private fun findPerson() {
        val (query, strategy) = peopleView.getQuery()
        val peopleFound = searchEngine.find(query, strategy)
        peopleView.showFoundPeople(peopleFound)
    }
}