package managers

import models.Category
import saver.IFileManager
import utils.ResultStatus
import java.util.UUID

class CategoryManager(private val fileManager: IFileManager) {

    fun deleteCategoryById(id: String): ResultStatus<String> {
        val validationResult = validateDeleteCategory(id)
        if (validationResult != null) return validationResult

        return try {
            fileManager.deleteObjectById(UUID.fromString(id), Category::class.java)
            ResultStatus.Success("Category deleted successfully!")
        } catch (e: Exception) {
            ResultStatus.Error("Failed to delete category: ${e.localizedMessage}")
        }
    }

    private fun validateDeleteCategory(id: String): ResultStatus<String>? {
        if (id.isBlank()) {
            return ResultStatus.Error("Invalid category ID.")
        }

        val category = fileManager.getObjectById(id, Category::class.java)
            ?: return ResultStatus.Error("Category not found.")

        return null
    }

    fun editCategory(category: Category): ResultStatus<Boolean> {
        fileManager.getObjectById(category.id.toString(), Category::class.java)
            ?: return ResultStatus.Error("This Id Not Found In File")

        fileManager.deleteObjectById(category.id, Category::class.java)
        fileManager.saveObject(category)

        return ResultStatus.Success(true)
    }
}