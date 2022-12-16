package io.volgadev.core.scenarioui.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.scenarioui.domain.model.FieldAnswer
import io.volgadev.core.scenarioui.domain.model.MainButtonAction
import io.volgadev.core.scenarioui.domain.model.PageStage
import io.volgadev.core.scenarioui.domain.model.QuestionnaireField
import io.volgadev.core.scenarioui.domain.model.ScenarioPage
import io.volgadev.core.scenarioui.presentation.ui.AdditionalFieldText
import io.volgadev.core.uikit.composable.button.MainScreenButton
import io.volgadev.core.uikit.theme.AppColors

@Composable
fun ScenarioPageScreen(
    modifier: Modifier,
    content: ScenarioPage
) {
    LazyColumn(
        modifier = modifier
    ) {
        content.stages.forEach { stage ->
            item {
                when (stage) {
                    is PageStage.Button -> {
                        MainScreenButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            text = stage.text
                        )
                    }
                    is PageStage.Field -> {
                        AdditionalFieldText(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            field = stage.field,
                            value = FieldAnswer(stage.field.defaultValue.orEmpty()),
                            onValueChange = {}
                        )
                    }
                    is PageStage.Image -> {

                    }
                    is PageStage.Text -> {
                        Text(
                            text = stage.text,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            color = AppColors.blackText,
                            fontSize = stage.size.sp,
                            fontWeight = if (stage.isBold) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ScenarioPageScreenPreview() {

    ScenarioPageScreen(
        modifier = Modifier.fillMaxSize(),
        content = ScenarioPage(
            stages = listOf(
                PageStage.Text(
                    "Покупайте наши бубы",
                    size = 24,
                    isBold = true
                ),
                PageStage.Text(
                    "Наши бубы самые лучшие на свете. Из покупают во всем мире уже 100 лет",
                    size = 18,
                    isBold = false
                ),
                PageStage.Image(url = "https://s0.rbk.ru/v6_top_pics/media/img/2/77/756171772426772.jpg"),
                PageStage.Field(
                    field = QuestionnaireField(
                        id = "",
                        displayName = "Введите имя",
                        required = true,
                        defaultValue = "Вася"
                    )
                ),
                PageStage.Button(text = "Поехали", action = MainButtonAction.ShowNext)
            )
        )
    )
}