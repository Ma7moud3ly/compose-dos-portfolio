package ma7moud3ly.github.io.feature.portfolio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ma7moud3ly.github.io.data.portfolio as portfolioData
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioEvent
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioState
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.model.Link
import ma7moud3ly.github.io.model.Portfolio
import ma7moud3ly.github.io.ui.theme.Phosphors

/** Holds the open tab, the picked item of each list and the screen color. */
class PortfolioViewModel(
    val portfolio: Portfolio = portfolioData
) : ViewModel() {

    var state by mutableStateOf(PortfolioState())
        private set

    /** The screen color, amber or green. Starts amber on every visit. */
    var phosphor by mutableStateOf(Phosphors.Amber)
        private set

    fun onEvent(event: PortfolioEvent) {
        when (event) {
            is PortfolioEvent.FinishBoot -> update { copy(booted = true) }
            is PortfolioEvent.OpenTab -> update { copy(tab = event.tab) }
            is PortfolioEvent.NextTab -> update { copy(tab = tab.next()) }
            is PortfolioEvent.PreviousTab -> update { copy(tab = tab.previous()) }
            is PortfolioEvent.Select -> select(event.index)
            is PortfolioEvent.MoveSelection -> select(state.selected(state.tab) + event.delta)
            is PortfolioEvent.TogglePhosphor -> togglePhosphor()
            // opened by the screen
            is PortfolioEvent.OpenLink, is PortfolioEvent.OpenSelected -> Unit
        }
    }

    /** The links of the item picked in the open tab. Empty when the tab has no list. */
    fun selectedLinks(): List<Link> {
        val index = state.selected(state.tab)
        val links = when (state.tab) {
            Tab.Apps -> portfolio.projects.getOrNull(index)?.links
            Tab.Books -> portfolio.writings.getOrNull(index)?.links
            Tab.Publications -> portfolio.publications.getOrNull(index)?.let { listOf(it.link) }
            else -> null
        }
        return links.orEmpty()
    }

    /** How many items the list of [tab] has. Zero when it has no list. */
    private fun itemCount(tab: Tab): Int = when (tab) {
        Tab.Apps -> portfolio.projects.size
        Tab.Books -> portfolio.writings.size
        Tab.Publications -> portfolio.publications.size
        else -> 0
    }

    /** Picks item [index] of the open tab's list, kept within the list. */
    private fun select(index: Int) {
        val count = itemCount(state.tab)
        if (count == 0) return
        update { copy(selection = selection + (tab to index.coerceIn(0, count - 1))) }
    }

    private fun togglePhosphor() {
        phosphor = Phosphors.other(phosphor)
    }

    private inline fun update(change: PortfolioState.() -> PortfolioState) {
        state = state.change()
    }
}
