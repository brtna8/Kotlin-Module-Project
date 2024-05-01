fun main() {
    val archives = mutableListOf<Archive>()

    fun createArchive() {
        println("Введите название нового архива:")
        val archiveName = readLine() ?: ""
        if (archiveName.isNotBlank()) {
            val newArchive = Archive(archiveName)
            archives.add(newArchive)
            println("Архив \"$archiveName\" создан.")
        } else {
            println("Название архива не может быть пустым.")
        }
    }

    fun archiveMenu() {
        while (true) {
            println("Список архивов:")
            println("0. Создать новый архив")
            archives.forEachIndexed { index, archive ->
                println("${index + 1}. ${archive.name}")
            }
            println("${archives.size + 1}. Выход")
            val input = readLine() ?: ""

            when {
                input == "0" -> {
                    createArchive()
                }
                input == "${archives.size + 1}" -> {
                    println("Выход из программы.")
                    return
                }
                input.toIntOrNull() != null -> {
                    val archiveIndex = input.toInt()
                    if (archiveIndex in 1..archives.size) {
                        archives[archiveIndex - 1].noteMenu()
                    } else {
                        println("Такого номера архива нет.")
                    }
                }
                else -> {
                    println("Некорректный ввод. Введите номер архива, 0 для создания нового или ${archives.size + 1} для выхода.")
                }
            }
        }
    }

    archiveMenu()
}

