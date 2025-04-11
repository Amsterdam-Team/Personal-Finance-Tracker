package managers
import models.Category
import models.Transaction
import saver.IFileManager
import utils.ResultStatus


class CategoryManager(private val fileManager: IFileManager) {

    fun editCategory(category: Category):ResultStatus<Boolean>{
        fileManager.getObjectById(category.id.toString(),Category::class.java)
            ?: return ResultStatus.Error("This Id Not Found In File")

        fileManager.deleteObjectById(category.id,Category::class.java)
        fileManager.saveObject(category)

        return ResultStatus.Success(true)
    }
}
