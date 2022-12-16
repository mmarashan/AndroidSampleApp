package io.volgadev.core.scenarioui.domain.model

/**
 * Дополнительное поле для заполнения с бэка
 * @property displayName отображаемое название поля
 * @property fieldType тип поля
 * @property id идентификатор поля
 * @property regexp регулярное выражение для валидации
 * @property required обязательное ли поле
 * @property rulesDisplayName отображаемые правила заполнения
 * @property defaultValue значение по-умолчанию для allowedValues
 */
data class QuestionnaireField(
    val id: String,
    val displayName: String,
    val fieldType: FieldType = FieldType.TEXT,
    val required: Boolean,
    val regexp: Regex? = null,
    val rulesDisplayName: String? = null,
    val defaultValue: String? = null
) {
    /**
     * Проверяет, является ли [answer] корректым ответом
     */
    fun checkAnswer(answer: FieldAnswer): Boolean {
        return if (required) {
            /**
             * Обазательное поле должно соответствовать [regexp]
             * или быть не пустым если [regexp] не задан
             */
            (regexp == null && answer.displayValue.isNotEmpty()) || (regexp != null && regexp.matches(
                answer.displayValue
            ))
        } else {
            /**
             * Необязательное поле должно быть пустым
             * или соответствовать [regexp] если он задан
             */
            answer.displayValue.isEmpty() || (regexp != null && regexp.matches(answer.displayValue))
        }
    }
}


/**
 * Результат ввода пользователя в доп.поле
 * @property displayValue вид для отображения
 */
data class FieldAnswer(
    val value: String? = null,
    val displayValue: String = value ?: ""
) {

    /* К виду для отправки в json */
    fun toJsonFieldValue(): String = value.toString()
}

/**
 * Тип поля. В первый итерации реализуется только текст - остальные пока не поддерживаются
 */
enum class FieldType {
    /* Текстовое поле */
    TEXT,

    /* Неизвестный/не поддеживаемый тип */
    UNKNOWN
}