package saver

import java.util.UUID

interface IFileManager {
    fun <T> saveObject(inputObject: T)

    fun <T> getAllObjects(inputObject: T): List<T>

    fun deleteObjectById(id: UUID)

    fun <T> getObjectById(inputObject: Class<T>, id: Int): T

}