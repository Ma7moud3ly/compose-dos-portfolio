package ma7moud3ly.github.io.feature.portfolio.model

/**
 * What the screen shows.
 *
 * @param booted whether the screen has switched on
 * @param tab the open tab
 * @param selection the picked item of each tab with a list; a missing tab picks its first item
 */
data class PortfolioState(
    val booted: Boolean = false,
    val tab: Tab = Tab.About,
    val selection: Map<Tab, Int> = emptyMap()
) {
    /** The picked item of [tab]. */
    fun selected(tab: Tab): Int = selection[tab] ?: 0
}
