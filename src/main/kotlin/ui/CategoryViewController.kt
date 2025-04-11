package ui

import models.Category
import java.util.*

// depend on categoryManager (val categoryManager:CategoryManager)
class CategoryViewController {
    fun addCategory(category: Category) {

        UiUtils.displayMessage("category added ${category.name}")
    }
    fun editCategory(id: UUID) {
        var category = UiUtils.getCategoryFromUser(id)
    }
    fun deleteCategory(id :UUID) {
        println("delete category ${id}")
    }
    fun viewAllCategories() {
        println("showing all categories")
    }
}