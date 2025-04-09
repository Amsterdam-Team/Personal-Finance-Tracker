package models

class Response<T> (val status: Boolean, val result:T)

// another Response class might be more efficient using sealed class
//sealed class Response<out T>{
//    data class Success<out T>(val result: T) : Response<T>()
//    data class Error(val result: String) : Response<Nothing>()
//}