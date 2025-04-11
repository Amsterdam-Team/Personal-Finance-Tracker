package managers

import Validators.isValidCategoryID
import Validators.isValidCategoryName
import models.Category
import saver.IFileManager
import utils.ResultStatus
import java.util.UUID

class CategoryManager(private val fileManager: IFileManager) {

    fun viewCategories(): ResultStatus<List<Category>> {
        val categories = fileManager.getAllObjects(Category::class.java)
        return validateViewCategories(categories)
    }

    private fun validateViewCategories(categories: List<Category>): ResultStatus<List<Category>> {
        return if (categories.isEmpty()) {
            ResultStatus.Empty("No categories found!")
        } else {
            ResultStatus.Success(categories)
        }
    }

    fun editCategory(category: Category): ResultStatus<Boolean> {
        fileManager.getObjectById(category.id.toString(), Category::class.java)
            ?: return ResultStatus.Error("This Id Not Found In File")

        fileManager.deleteObjectById(category.id, Category::class.java)
        fileManager.saveObject(category)

        return ResultStatus.Success(true)
    }

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

    fun addCategory(id: UUID, name: String): ResultStatus<String> {
        val categories = fileManager.getAllObjects(Category::class.java)
        if (listOf(
                isValidCategoryID(categories, id),
                isValidCategoryName(categories, name)
            ).all { it == ResultStatus.Success("success") }
        ) {
            fileManager.saveObject(Category(id, name))
            return ResultStatus.Success("Category Added Successfully!")
        }
        return ResultStatus.Error("Invalid Data")
    }
   
    fun isValidCategoryToEdit(categoryName:String,categoryID:String): ResultStatus<String>{
        val oldObject=fileManager.getObjectById(categoryID, Category::class.java)
        if ((categoryName.matches(Regex("^[A-Za-z]+$"))) && oldObject != null)
            return ResultStatus.Success("Success Editing")

        return ResultStatus.Error("Please Enter Valid Id and Name")
    }

}
