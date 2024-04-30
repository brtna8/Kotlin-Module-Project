object NotesApp {
    val archives = mutableListOf<Archive>()


    @JvmStatic
    fun main(args: Array<String>) {
        Archive.archiveMenu()
    }
}