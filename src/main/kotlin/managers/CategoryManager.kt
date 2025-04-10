package managers
import models.Category
import saver.IFileManager

class CategoryManager(private val fileManager: IFileManager) {


    fun checkAddCategory(categoryName:String):Boolean{
        return false
    }
    fun checkDeleteCategory(categoryId:Any?):Boolean{
        return false
    }
    fun checkViewCategory(categories:List<Category>):Boolean{
        return false
    }
    fun checkEditCategory(categoryName:String,categoryId:Any):Boolean{
        return false
    }
}