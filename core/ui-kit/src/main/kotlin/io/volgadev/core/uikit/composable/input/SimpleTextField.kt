package io.volgadev.core.uikit.composable.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.core.uikit.theme.AppTypography

/**
 * Значение из TextFieldImpl - TextFieldPadding
 * В текущей реализации [TextField] paddings захардкожены этим значением
 * Мы создали issue https://issuetracker.google.com/issues/204421875 и надеемся,
 * что в будущих версиях это поправят, и можно будет задавать paddings только через modifier
 * @author mmarashan
 */
internal val TextFieldPadding = 16.dp

/**
 * Цвета по умолчанию для SimpleTextFiled
 */
val textFieldColors: TextFieldColors
    @Composable get() = TextFieldDefaults.textFieldColors(
        textColor = AppColors.blackText,
        disabledTextColor = AppColors.grayText,
        cursorColor = AppColors.grayText,
        backgroundColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        placeholderColor = AppColors.grayText,
        disabledPlaceholderColor = AppColors.grayText
    )

/**
 * Поле ввода с маской и заголовком без фона. Минимальный отступ - 16.dp
 * @param value введенное строковое значение
 * @param modifier модификатор,
 * @param label текст заголовка и подсказка при пустом [value]
 * @param showErrorOnLabel нужно ли отображать ошибку на заголовке
 * @param placeholder подсказка при фокусе на поле
 * @param description описание поля ввода. Располагается под разделителем, если указана
 * @param error строка с ошибкой (наличие этой строки переводит поле в состояние показа ошибки)
 * @param verticalPadding вертикальный отступ, не меньше 16.dp
 * @param horizontalPadding горизонтальный отступ, не меньше 16.dp
 * @param maxLength ограничение длины ввода строки
 * @param visualTransformation преобразование визуального представление входного значения
 * @param keyboardOptions параметры программной клавиатуры, содержащие такие настройки,
 * как KeyboardType и ImeAction.
 * @param keyboardActions когда выдается IME действие, вызывается соответствующий обратный вызов
 * @param enabled при значении false текстовое поле не будет ни редактируемым, ни фокусируемым,
 * ввод текстового поля будет недоступен для выбора.
 * @param readOnly управляет редактируемым состоянием TextField.
 * При значении true текстовое поле нельзя изменить, однако
 * пользователь может выделить его и скопировать из него текст.
 * Текстовые поля только для чтения обычно используются для отображения
 * предварительно заполненных форм, которые пользователь не может редактировать.
 * @param singleLine если установлено значение true, это текстовое поле становится
 * одним текстовым полем с горизонтальной прокруткой вместо переноса на несколько строк.
 * @param focusRequester испольуется для отправки запросов на изменение фокуса
 * @param trailingIcon значок в конце поля ввода
 * @param onFocusEvent Обратный вызов, который вызывается всякий раз,
 * когда система фокуса вызывает события.
 * @param onValueChange callback на изменение значения
 */
@Composable
fun SimpleTextField(
    value: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    description: String? = null,
    error: String? = null,
    verticalPadding: Dp = 0.dp,
    horizontalPadding: Dp = 0.dp,
    maxLength: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    focusRequester: FocusRequester = FocusRequester(),
    colors: TextFieldColors = textFieldColors,
    trailingIcon: @Composable (() -> Unit)? = null,
    onFocusEvent: (FocusState) -> Unit = { },
    onValueChange: (String) -> Unit = {}
) {

    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }
    val textFieldValue = textFieldValueState.copy(text = value)

    SimpleTextField(
        value = textFieldValue,
        modifier = modifier,
        placeholder = placeholder,
        description = description,
        error = error,
        label = label,
        verticalPadding = verticalPadding,
        horizontalPadding = horizontalPadding,
        maxLength = maxLength,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        focusRequester = focusRequester,
        colors = colors,
        trailingIcon = trailingIcon,
        onFocusEvent = onFocusEvent,
        onValueChange = { newText ->
            textFieldValueState = newText
            if (value != newText.text) {
                onValueChange(newText.text)
            }
        })
}

/**
 * Поле ввода с маской и заголовком без фона. Минимальный отступ - 16.dp
 * @param value ввод типа [TextFieldValue] для отображения в текстовом поле
 * @param modifier модификатор,
 * @param label текст заголовка и подсказка при пустом [value]
 * @param placeholder подсказка при фокусе на поле
 * @param description описание поля ввода. Располагается под разделителем, если указана
 * @param error строка с ошибкой (наличие этой строки переводит поле в состояние показа ошибки)
 * @param verticalPadding вертикальный отступ, не меньше 16.dp
 * @param horizontalPadding горизонтальный отступ, не меньше 16.dp
 * @param maxLength ограничение длины ввода строки
 * @param visualTransformation преобразование визуального представление входного значения
 * @param keyboardOptions параметры программной клавиатуры, содержащие такие настройки,
 * как KeyboardType и ImeAction.
 * @param keyboardActions когда выдается IME действие, вызывается соответствующий обратный вызов
 * @param enabled при значении false текстовое поле не будет ни редактируемым, ни фокусируемым,
 * ввод текстового поля будет недоступен для выбора.
 * @param readOnly управляет редактируемым состоянием TextField.
 * При значении true текстовое поле нельзя изменить, однако
 * пользователь может выделить его и скопировать из него текст.
 * Текстовые поля только для чтения обычно используются для отображения
 * предварительно заполненных форм, которые пользователь не может редактировать.
 * @param singleLine если установлено значение true, это текстовое поле становится
 * одним текстовым полем с горизонтальной прокруткой вместо переноса на несколько строк.
 * @param focusRequester испольуется для отправки запросов на изменение фокуса
 * @param trailingIcon значок в конце поля ввода
 * @param onFocusEvent Обратный вызов, который вызывается всякий раз,
 * когда система фокуса вызывает события.
 * @param onValueChange callback на изменение значения
 */
@Composable
fun SimpleTextField(
    value: TextFieldValue,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    description: String? = null,
    error: String? = null,
    verticalPadding: Dp = 0.dp,
    horizontalPadding: Dp = 0.dp,
    maxLength: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    focusRequester: FocusRequester = FocusRequester(),
    colors: TextFieldColors = textFieldColors,
    trailingIcon: @Composable (() -> Unit)? = null,
    onFocusEvent: (FocusState) -> Unit = { },
    onValueChange: (TextFieldValue) -> Unit
) {
    Column(
        modifier = modifier.padding(
            vertical = max(verticalPadding - TextFieldPadding + 1.dp, 1.dp),
            horizontal = max(horizontalPadding - TextFieldPadding, 0.dp)
        ),
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusEvent(onFocusEvent),
            value = value,
            onValueChange = {
                if (it.text.length <= maxLength) {
                    onValueChange(
                        it.copy(
                            text = it.text
                        )
                    )
                }
            },
            textStyle = AppTypography.subtitle1,
            label = {
                label?.let {
                    Text(
                        text = it, color = if (error == null) {
                            AppColors.grayText
                        } else {
                            AppColors.primaryOrange
                        }
                    )
                }
            },
            placeholder = {
                placeholder?.let {
                    Text(
                        text = it,
                        color = AppColors.grayText,
                        style = AppTypography.subtitle1,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            singleLine = singleLine,
            colors = colors,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            enabled = enabled,
            readOnly = readOnly,
            trailingIcon = trailingIcon
        )
        Divider(
            color = AppColors.grayBackground,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = TextFieldPadding)
        )
        description?.let {
            SimpleTextFieldDescriptionIndicator(
                text = it, color = AppColors.grayText
            )
        }
        error?.let {
            SimpleTextFieldDescriptionIndicator(
                text = it, color = AppColors.primaryOrange
            )
        }
    }
}

/**
 * Индикатор ошибки для поля [SimpleTextField]
 * @param text строка с ошибкой
 * @param modifier модификатор
 */
@Composable
@Suppress("MagicNumber")
internal fun SimpleTextFieldDescriptionIndicator(
    text: String, modifier: Modifier = Modifier, color: Color = AppColors.grayText
) {
    Text(
        text = text,
        color = color,
        overflow = TextOverflow.Ellipsis,
        style = AppTypography.body2,
        modifier = modifier.padding(horizontal = TextFieldPadding, vertical = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember", "StringLiteralDuplication", "MagicNumber")
private fun SimpleTextFieldPreview() {
    SimpleTextField(
        value = "",
        label = "Введите город",
        modifier = Modifier.fillMaxWidth(),
        verticalPadding = 16.dp,
        horizontalPadding = 24.dp
    )
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember", "StringLiteralDuplication", "MagicNumber")
private fun SimpleTextFieldPreview2() {
    SimpleTextField(
        value = "Самара",
        label = "Введите город",
        description = "Введите ваш любимый город",
        modifier = Modifier.fillMaxWidth(),
        verticalPadding = 16.dp,
        horizontalPadding = 24.dp
    )
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember", "StringLiteralDuplication", "MagicNumber")
private fun SimpleTextFieldPreview3() {
    SimpleTextField(
        value = "Самара",
        label = "Введите город",
        description = "Введите ваш любимый город",
        error = "Неверная Введите город",
        modifier = Modifier.fillMaxWidth(),
        verticalPadding = 16.dp,
        horizontalPadding = 24.dp
    )
}

@Preview(showBackground = true)
@Composable
private fun SimpleTextFieldDisabledPreview() {
    SimpleTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        label = "Введите город",
        description = "Введите ваш любимый город",
        enabled = false,
        verticalPadding = 16.dp,
        horizontalPadding = 24.dp
    )
}
