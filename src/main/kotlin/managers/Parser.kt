package managers

import java.lang.Exception
import java.time.LocalDate
import java.util.UUID
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

object Parser {
    fun parseObjectFromString(input: String, className: String): Any? {
        val openParentheses = input.indexOf('(')
        val closedParentheses = input.lastIndexOf(')')
        val inner = input.substring(openParentheses + 1, closedParentheses)

        val kClass = Class.forName(className).kotlin

        val constructor = kClass.primaryConstructor?: throw NullPointerException()
        val args = mutableMapOf<KParameter, Any?>()

        val parameterPairs = splitTopLevel(inner)

        for (param in constructor.parameters){
            val paramName = param.name
            val expectedType = param.type.jvmErasure

            val valueStr = parameterPairs.find { it.startsWith("$paramName=") }?.substringAfter("=")

            val value = when{

                valueStr?.contains('(') == true && valueStr.endsWith(")") -> {
                    val openParen = valueStr.indexOf('(')
                    parseObjectFromString(valueStr, "models.${valueStr.substring(0, openParen)}")
                }

                expectedType.java.isEnum -> {
                    val enumConstants = expectedType.java.enumConstants
                    enumConstants.firstOrNull { (it as Enum<*>).name == valueStr }
                }

                expectedType == Int::class -> valueStr?.toIntOrNull()
                expectedType == UUID::class -> UUID.fromString(valueStr)
                expectedType == Double::class -> valueStr?.toDoubleOrNull()
                expectedType == String::class -> valueStr
                expectedType == LocalDate::class -> LocalDate.parse(valueStr)

                else -> null
            }
            args[param] = value
        }
        return constructor.callBy(args)
    }

    private fun splitTopLevel(input: String): List<String>{
        val pattern = Regex("""\w+=((\w+\([^()]*\))|[^,()]*)""")
        return pattern.findAll(input).map { it.value.trim() }.toList()
    }
}