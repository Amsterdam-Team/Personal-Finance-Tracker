package managers
import models.Category
import saver.IFileManager
import utils.ResultStatus
import java.util.*

class CategoryManager(private val fileManager: IFileManager) {


    fun addCategory(id: UUID, name: String): ResultStatus<String> {
        val categories = fileManager.getAllObjects(Category::class.java)
        if (listOf(
                isValidCategoryID(categories, id),
                isValidCategoryName(categories, "Salary")
            ).all { it == ResultStatus.Success("success") }
        ) {
            fileManager.saveObject(Category(UUID.randomUUID(), name))
            return ResultStatus.Success("Category Added Successfully!")
        }
        return ResultStatus.Error("Invalid Data")
    }
}

    fun isValidCategoryID(categories: List<Category>, id: UUID): ResultStatus<String> {
        if (id.toString().isBlank() || id.toString().contains(" "))
            return ResultStatus.Error("Invalid Id")
        else {
            val category = categories.find { it.id == id }
            if (category == null)
                return ResultStatus.Success("success")
        }
        return ResultStatus.Success("success")
    }
    fun isValidCategoryName(categories:List<Category>,name:String):ResultStatus<String>{
        if(name.isBlank() || name.contains(' '))
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

