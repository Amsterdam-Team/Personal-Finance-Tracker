package saver

import java.util.UUID

interface IFileManager {
    fun <T> saveObject(inputObject: T)

    fun <T> getAllObjects(inputObject: Class<T>): List<T>

    fun <T>deleteObjectById(id: UUID, inputObject: Class<T>)

    fun <T>getObjectById(id: String, inputObject: Class<T>): T?
}