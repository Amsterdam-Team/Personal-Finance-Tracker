package managers
import models.Category
import saver.IFileManager
import utils.ResultStatus
import java.util.*

class CategoryManager(private val fileManager: IFileManager) {


    fun editCategory(category: Category):ResultStatus<Boolean>{
        val result = isValidCategoryForEdit(category.name,category.id)
        if(!result)return ResultStatus.Error("Invalid Category Name Or Id")

        fileManager.deleteObjectById(category.id,Category::class.java)
        fileManager.saveObject(category)

        return ResultStatus.Success(true)
    }

    private fun isValidCategoryForEdit(categoryName:String, categoryId:Any):Boolean{
        return ((categoryName.matches(Regex("^[A-Za-z]+$"))) && (categoryId is UUID))
    }


}
