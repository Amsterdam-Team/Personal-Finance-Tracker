package managers
import models.Category
import saver.IFileManager
import utils.ResultStatus

class CategoryManager(private val fileManager: IFileManager) {


    fun editCategory(category: Category):ResultStatus<Boolean>{
        val result = checkEditCategory(category.name,category.id)
        if(!result)return ResultStatus.Error("Invalid Category Name Or Id")

        //delete the old value and save the new
        //fileManager.deleteObjectById(category.id)
        fileManager.saveObject(category)

        return ResultStatus.Success(true)
    }

    private fun checkAddCategory(categoryName:String):Boolean{
        return false
    }
    private fun checkDeleteCategory(categoryId:Any?):Boolean{
        return false
    }
    private fun checkViewCategory(categories:List<Category>):Boolean{
        return false
    }
    private fun checkEditCategory(categoryName:String,categoryId:Any):Boolean{
        return ((categoryName.matches(Regex("^[A-Za-z]+$"))) && (categoryId is Int && categoryId > 0))
    }
}