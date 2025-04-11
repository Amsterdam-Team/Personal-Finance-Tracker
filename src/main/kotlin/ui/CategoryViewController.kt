package ui

import managers.CategoryManager
import models.Category
import utils.ResultStatus
import utils.Validator
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
        viewAllCategories()
        val result = categoryManager.deleteCategoryById(id.toString())
        when(result){
            is ResultStatus.Success -> {
                UiUtils.displayMessage("${id} deleted successfully")
            }
            is ResultStatus.Error -> {
                UiUtils.displayMessage(result.errorMessage)
            }
            is ResultStatus.Empty -> {
                UiUtils.displayMessage(result.message)
            }
        }
    }
    fun viewAllCategories() {
        val result = categoryManager.viewCategories()
        when (result) {
            is ResultStatus.Success -> {
                for (category in result.data) {
                    UiUtils.displayMessage("${category.id}: ${category.name}")
                }
            }
            is ResultStatus.Error -> {
                UiUtils.displayMessage(result.errorMessage)
            }
            is ResultStatus.Empty -> {
                UiUtils.displayMessage(result.message)
            }
        }
    }
}