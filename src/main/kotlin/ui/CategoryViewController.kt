package ui

import managers.CategoryManager
import models.Category
import utils.ResultStatus
import java.util.*

// depend on categoryManager
class CategoryViewController(val categoryManager: CategoryManager) {
    fun addCategory(category: Category) {
        val result =categoryManager.addCategory(id = category.id, name = category.name)
        if (result is ResultStatus.Success) {
            UiUtils.displayMessage("${category.name} added successfully")
        }else if (result is ResultStatus.Error){
            UiUtils.displayMessage(result.errorMessage)
        }
    }
    fun editCategory(id: UUID) {
        var category = UiUtils.getCategoryFromUser(id)
        var result = categoryManager.editCategory(category)
        if (result is ResultStatus.Success) {
            UiUtils.displayMessage("${category.name} edited successfully")
        }else if (result is ResultStatus.Error){
            UiUtils.displayMessage(result.errorMessage)
        }
    }
    fun deleteCategory(id :UUID) {
        println("delete category ${id}")
    }
    fun viewAllCategories() {
        println("showing all categories")
    }
}