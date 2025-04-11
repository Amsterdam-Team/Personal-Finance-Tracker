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

    private fun checkAddCategory(categoryName: String): Boolean {
        return false
    }

    private fun checkDeleteCategory(categoryId: Any?): Boolean {
        return false
    }

    private fun checkViewCategory(categories: List<Category>): Boolean {
        return false
    }

    private fun checkEditCategory(categoryName: String, categoryId: Any): Boolean {
        return false
    }
}