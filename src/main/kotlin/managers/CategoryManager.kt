package managers

import models.Category
import saver.IFileManager
import utils.ResultStatus

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
}