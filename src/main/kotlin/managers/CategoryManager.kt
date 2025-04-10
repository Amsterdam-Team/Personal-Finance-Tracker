package managers
import models.Category
import saver.IFileManager

class CategoryManager(private val fileManager: IFileManager) {


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
        return false
    }
}