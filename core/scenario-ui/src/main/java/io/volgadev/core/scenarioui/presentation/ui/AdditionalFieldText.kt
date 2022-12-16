package io.volgadev.core.scenarioui.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.volgadev.core.scenarioui.domain.model.FieldAnswer
import io.volgadev.core.scenarioui.domain.model.FieldType
import io.volgadev.core.scenarioui.domain.model.QuestionnaireField
import io.volgadev.core.uikit.composable.input.SimpleTextField


@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun AdditionalFieldText(
    modifier: Modifier,
    field: QuestionnaireField,
    value: FieldAnswer,
    onValueChange: (FieldAnswer) -> Unit
) {
    val regex = field.regexp
    val valueStr = value.displayValue
    val showError = valueStr.isNotEmpty() && regex != null && !regex.matches(valueStr)
    val keyboardController = LocalSoftwareKeyboardController.current

    SimpleTextField(
        modifier = modifier,
        value = valueStr,
        label = field.displayName,
        description = if (showError) {
            null
        } else {
            field.rulesDisplayName
        },
        error = if (showError) {
            field.rulesDisplayName
        } else {
            null
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            },
        ),
        onValueChange = {})
}

@Preview(showBackground = true)
@Composable
private fun AdditionalFieldsContentPreview() {
    Column {
        AdditionalFieldText(modifier = Modifier, field = QuestionnaireField(
            id = "",
            fieldType = FieldType.TEXT,
            displayName = "",
            rulesDisplayName = "",
            regexp = Regex("^[\\\\dа-яА-ЯёЁ]{2,30}$"),
            required = true
        ), value = FieldAnswer("Яй"), onValueChange = {})
        Spacer(modifier = Modifier.height(16.dp))
    }
}
