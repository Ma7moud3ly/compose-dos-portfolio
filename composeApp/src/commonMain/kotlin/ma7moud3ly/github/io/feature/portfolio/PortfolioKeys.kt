package ma7moud3ly.github.io.feature.portfolio

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioEvent
import ma7moud3ly.github.io.feature.portfolio.model.Tab

/** The number keys, in the order of the tabs they open. */
private val NumberKeys = listOf(Key.One, Key.Two, Key.Three, Key.Four, Key.Five, Key.Six, Key.Seven, Key.Eight, Key.Nine)

/**
 * The event this key press stands for, or null when the key does nothing.
 *
 * Until the screen is [booted], any key finishes the boot. After it:
 * - 1 to 6 open a tab, and the left and right arrows switch tabs
 * - the up and down arrows move the pick of the open tab's list
 * - Enter opens the first link of the picked item
 * - C switches the screen color
 */
internal fun KeyEvent.toPortfolioEvent(booted: Boolean): PortfolioEvent? {
    if (type != KeyEventType.KeyDown) return null
    if (!booted) return PortfolioEvent.FinishBoot
    val number = NumberKeys.indexOf(key)
    if (number in Tab.entries.indices) return PortfolioEvent.OpenTab(Tab.entries[number])
    return when (key) {
        Key.DirectionRight -> PortfolioEvent.NextTab
        Key.DirectionLeft -> PortfolioEvent.PreviousTab
        Key.DirectionDown -> PortfolioEvent.MoveSelection(1)
        Key.DirectionUp -> PortfolioEvent.MoveSelection(-1)
        Key.Enter -> PortfolioEvent.OpenSelected
        Key.C -> PortfolioEvent.TogglePhosphor
        else -> null
    }
}
