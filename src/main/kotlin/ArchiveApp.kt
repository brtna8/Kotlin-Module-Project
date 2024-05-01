import java.util.Scanner

class Archive(val name: String) {
    private val notes = mutableListOf<Note>()

    private val scanner = Scanner(System.`in`)

    private fun addNote() {
        println("Введите название заметки: ")
        var title = scanner.nextLine()
        while (title.isBlank()) {
            println("Название заметки не может быть пустым. Повторите ввод:")
            title = scanner.nextLine()
        }

        println("Введите содержимое заметки:")
        var content = scanner.nextLine()
        while (content.isBlank()) {
            println("Текст заметки не может быть пустым. Повторите ввод:")
            content = scanner.nextLine()
        }

        val newNote = Note(title, content)
        notes.add(newNote)
        println("Заметка \"$title\" добавлена в архив \"$name\".")
    }

    fun noteMenu() {
        while (true) {
            println("Текущий архив: $name")
            println("Список заметок:")
            println("0. Создать новую заметку")
            notes.forEachIndexed { index, note ->
                println("${index + 1}. ${note.title}")
            }
            println("${notes.size + 1}. Выход к списку архивов")
            println("Выберите заметку: ")
            val input = readLine() ?: ""

            when {
                input == "0" -> {
                    addNote()
                }
                input == "${notes.size + 1}" -> {
                    println("Возврат к выбору архива.")
                    return
                }
                input.toIntOrNull() != null -> {
                    val noteIndex = input.toInt()
                    if (noteIndex in 1..notes.size) {
                        notes[noteIndex - 1].viewNoteContent()
                    } else {
                        println("Такой заметки в архиве нет.")
                    }
                }
                else -> {
                    println("Некорректный ввод. Введите номер заметки, 0 для создания новой или ${notes.size + 1} для возврата.")
                }
            }
        }
    }
}
