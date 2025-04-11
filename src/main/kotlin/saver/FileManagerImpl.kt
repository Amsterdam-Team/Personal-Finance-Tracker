package saver

import managers.Parser
import java.io.File
import java.io.ObjectInput
import java.util.*

class FileManagerImpl: IFileManager {
    private val stackTrace = Thread.currentThread().stackTrace
    private val caller = if (stackTrace.firstOrNull { it.methodName == "main" }?.className == "MainKt") "main" else "test"
    override fun <T> saveObject(inputObject: T) {
        val file = File("src/main/kotlin/saver/saveFile$caller.txt")
        if (!file.exists()){
            file.createNewFile()
        }
        if (inputObject != null){
            file.appendText("${inputObject}\n")
        }
    }

    override fun <T> getAllObjects(inputObject: Class<T>): List<T> {
        val returnList = mutableListOf<T>()
        val file = File("src/main/kotlin/saver/saveFile$caller.txt")
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

    override fun <T>deleteObjectById(id: UUID, inputObject: Class<T>) {
        val file = File("src/main/kotlin/saver/saveFile$caller.txt")
        if (!file.exists()){
            file.createNewFile()
        }
        val className = inputObject.toString().replace("class models.", "")
        val updatedLines = file.readLines().filterNot { it.contains(id.toString()) && it.startsWith(className) }
        file.writeText(updatedLines.joinToString("\n"))
    }

    override fun <T> getObjectById(id: String, inputObject: Class<T>): T? {
        val file = File("src/main/kotlin/saver/saveFile$caller.txt")
         var returnObject: T? = null
        if (!file.exists()){
            file.createNewFile()
        }
        val actualClassName = inputObject.toString().replace("class ", "")
        val className = inputObject.toString().replace("class models.", "")
        file.forEachLine {line ->
            if (line.contains(id) && line.startsWith(className)){
                returnObject = Parser.parseObjectFromString(line, actualClassName) as T
            }
        }
        return returnObject
    }
}