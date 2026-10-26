package ma7moud3ly.github.io.feature.portfolio.model

/**
 * The tabs of the portfolio, in order. Each is opened by the number key of
 * its position, from 1.
 *
 * @param path the DOS folder shown as the tab's prompt and window title
 */
enum class Tab(val label: String, val path: String) {
    About("ABOUT", "C:\\ABOUT"),
    Stack("STACK", "C:\\STACK"),
    Apps("APPS", "C:\\APPS"),
    Books("BOOKS", "C:\\BOOKS"),
    Publications("PUBS", "C:\\PUBS");

    /** The number key that opens this tab. */
    val number: Int get() = ordinal + 1

    /** The tab after this one, wrapping around to the first. */
    fun next(): Tab = entries[(ordinal + 1) % entries.size]

    /** The tab before this one, wrapping around to the last. */
    fun previous(): Tab = entries[(ordinal - 1 + entries.size) % entries.size]
}
