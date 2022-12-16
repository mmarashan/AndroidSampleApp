package io.volgadev.core.scenarioui.domain.model

data class ScenarioPage(
    val stages: List<PageStage>
)

sealed class PageStage {

    data class Text(val text: String, val size: Int = 16, val isBold: Boolean = false) : PageStage()
    data class Image(val url: String) : PageStage()
    data class Button(val text: String, val action: MainButtonAction) : PageStage()

    data class Field(val field: QuestionnaireField) : PageStage()

}

sealed class MainButtonAction {
    class Navigate(val destination: String) : MainButtonAction()
    object ShowNext : MainButtonAction()
    object SaveForm : MainButtonAction()
}