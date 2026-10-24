package ma7moud3ly.github.io.feature.portfolio.model

import ma7moud3ly.github.io.model.Link

/**
 * What the visitor did, by key, click or tap.
 *
 * Goes from the screen to PortfolioViewModel. [OpenLink] and [OpenSelected]
 * are carried out by the screen, which opens the browser.
 */
sealed interface PortfolioEvent {
    // boot
    data object FinishBoot : PortfolioEvent

    // tabs
    data class OpenTab(val tab: Tab) : PortfolioEvent
    data object NextTab : PortfolioEvent
    data object PreviousTab : PortfolioEvent

    // the list of the open tab
    data class Select(val index: Int) : PortfolioEvent
    data class MoveSelection(val delta: Int) : PortfolioEvent

    // links
    data class OpenLink(val link: Link) : PortfolioEvent
    data object OpenSelected : PortfolioEvent

    // screen
    data object TogglePhosphor : PortfolioEvent
}
