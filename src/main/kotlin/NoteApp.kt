import java.util.Scanner

class Note(val title: String, var content: String) {
    companion object {
        private val scanner = Scanner(System.`in`)
        fun noteMenu(archive: Archive) {
            while (true) {
                println("Текущий архив: ${archive.name}")
                println("Список заметок:")
                val lastNoteIndex = Archive.notes.size
                println("0. Создать новую заметку")
                Archive.notes.forEachIndexed { index, note ->
                    println("${index + 1}. ${note.title}")
                }
                println("${lastNoteIndex + 1}. Выход к списку архивов")
                println("Выберите заметку: ")
                val input = scanner.nextLine()

                when {
                    input == "0" -> {
                        createNote()
                    }

                    input == "${lastNoteIndex + 1}" -> {
                        println("Возврат к выбору архива.")
                        return
                    }

                    input.toIntOrNull() != null -> {
                        val noteIndex = input.toInt()
                        if (noteIndex in 1..Archive.notes.size) {
                            viewNoteContent(Archive.notes[noteIndex - 1])
                            var back = false
                            while (!back) {
                                val inputNewContent = scanner.nextLine()
                                when {
                                    inputNewContent == "0" -> {
                                        println("Введите дополнительный текст заметки")
                                        val updateNote = scanner.nextLine()
                                        writeMore(noteIndex - 1, updateNote)
                                        viewNoteContent(Archive.notes[noteIndex - 1])
                                    }
                                    else -> {
                                        println("Возврат к выбору заметки.")
                                        back = true
                                    }
                                }
                            }
                        } else {
                            println("Такой заметки в архиве нет.")
                        }
                    }
                    else -> {
                        println("Некорректный ввод. Введите номер заметки, 0 для создания новой или ${lastNoteIndex + 1} для возврата.")
                    }
                }
            }
        }

        private fun writeMore(noteIndex: Int, newcontent: String) {
            Archive.notes[noteIndex].content = Archive.notes[noteIndex].content + " " + newcontent
        }

        private fun createNote() {
            println("Введите название заметки: ")
            val noteTitle = userInput()
            when {
                noteTitle.isNotBlank() -> {
                    println("Введите содержимое новой заметки:")
                    val noteContent = userInput()

                    val newNote = Note(noteTitle, noteContent)
                    Archive.addNote(newNote)
                    println("Заметка \"$noteTitle\" добавлена в архив.")
                }

                noteTitle == "${Archive.notes.size + 1}" -> {
                    println("Выход")
                    return
                }
            }
        }
        private fun viewNoteContent(note: Note) {
            println("Параметры заметки: ")
            println("Название заметки: ${note.title}")
            println("Текст заметки: ${note.content}")
            println("Введите 0 для изменения текста или любой другой символ, для возврата к списку заметок")
        }
        private fun userInput(): String {
            var arNoteName: String
            arNoteName = Scanner(System.`in`).nextLine()
            while (arNoteName.isBlank()) {
                println("Не может быть пусто, повторите ввод")
                arNoteName = Scanner(System.`in`).nextLine()
            }
            return arNoteName
        }
    }
}