package calculator.domain

import calculator.vo.IntNumber
import calculator.vo.Numbers

object Parser {

    private val DEFAULT_NUMBERS = Numbers(listOf(IntNumber(0)))
    private val CUSTOM_DELIMITER = Regex("//(.)\n(.*)")
    private val DEFAULT_DELIMITERS = arrayOf(",", ":")

    fun parse(expression: String?): Numbers {
        if (expression.isNullOrEmpty()) return DEFAULT_NUMBERS
        return Numbers(prepareParse(expression))
    }

    private fun prepareParse(expression: String) =
        CUSTOM_DELIMITER.find(expression)?.let {
            val (_, delimiterValue, expressionValue) = it.groupValues
            splitNumbers(expressionValue, delimiterValue)
        } ?: splitNumbers(expression, *DEFAULT_DELIMITERS)

    private fun splitNumbers(expression: String, vararg regex: String) =
        expression.split(*regex)
            .map(String::trim)
            .map(IntNumber::fromString)
}
