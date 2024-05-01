class Note(val title: String, private var content: String) {
    init {
        require(title.isNotBlank()) { "Название заметки не может быть пустым" }
        require(content.isNotBlank()) { "Текст заметки не может быть пустым" }
    }

    fun viewNoteContent() {
        println("Параметры заметки: ")
        println("Название заметки: $title")
        println("Текст заметки: $content")
    }
}