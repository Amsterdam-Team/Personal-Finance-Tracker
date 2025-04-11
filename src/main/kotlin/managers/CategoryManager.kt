package managers
import models.Category
import models.Transaction
import saver.IFileManager
import utils.ResultStatus
import java.util.*



class CategoryManager(private val fileManager: IFileManager) {

    fun editCategory(category: Category): ResultStatus<Boolean> {
        fileManager.getObjectById(category.id.toString(), Category::class.java)
            ?: return ResultStatus.Error("This Id Not Found In File")

        fileManager.deleteObjectById(category.id, Category::class.java)
        fileManager.saveObject(category)

        return ResultStatus.Success(true)
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

    fun isValidCategoryID(categories: List<Category>, id: UUID): ResultStatus<String> {
        if (id.toString().isBlank() || id.toString().contains(" "))
            return ResultStatus.Error("Invalid Id")
        else {
            val category = categories.find { it.id == id }
            if (category == null)
                return ResultStatus.Success("success")
        }
        return ResultStatus.Error("Id Already Exists")
    }

    fun isValidCategoryName(categories: List<Category>, name: String): ResultStatus<String> {
        if (name.isBlank() || name.contains(' '))
            return ResultStatus.Error("Invalid Name")
        else {
            if (name.matches(Regex("^[a-zA-Z ]+$"))) {
                val category = categories.find { it.name == name }
                if (category == null)
                    return ResultStatus.Success("success")
            }
        }
        return ResultStatus.Error("Invalid Name")
    }
}
