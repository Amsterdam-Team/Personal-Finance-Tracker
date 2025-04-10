package saver

import managers.Parser
import models.Category
import java.io.File
import java.time.LocalDate
import java.util.*
import kotlin.reflect.full.primaryConstructor

class FileManagerImpl: IFileManager {
    override fun <T> saveObject(inputObject: T) {
        val file = File("src/main/kotlin/saver/saveFile.txt")
        if (!file.exists()){
            file.createNewFile()
        }
        if (inputObject != null){
            file.appendText("${inputObject}\n")
        }
    }

    override fun <T> getAllObjects(inputObject: T): List<T> {
        val returnList = mutableListOf<T>()
        val file = File("src/main/kotlin/saver/saveFile.txt")
        if (!file.exists()){
            file.createNewFile()
        }
        val className = inputObject.toString().replace("class models.", "")
        val actualClassName = inputObject.toString().replace("class ", "")
        file.forEachLine { line ->
            if (line.startsWith(className)){
                val objectReturn = Parser.parseObjectFromString(line, actualClassName)
                returnList.add(objectReturn as T)
            }
        }
        return returnList
    }

    override fun deleteObjectById(id: UUID) {
        val file = File("src/main/kotlin/saver/saveFile.txt")
        if (!file.exists()){
            file.createNewFile()
        }
        val updatedLines = file.readLines().filterNot { it.contains(id.toString()) }
        file.writeText(updatedLines.joinToString("\n"))
    }
}