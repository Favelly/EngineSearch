import java.io.File

fun main(args: Array<String>) {
    val peopleController =
        if (args.isNotEmpty() && args[0] == "--data")
            PeopleController(File(args[1]))
        else
            PeopleController()
    peopleController.launch()
}