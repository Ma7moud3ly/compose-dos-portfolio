package ma7moud3ly.github.io.model

/**
 * Everything the portfolio shows.
 *
 * @param source the repository of the portfolio's own code
 */
data class Portfolio(
    val profile: Profile,
    val projects: List<Project>,
    val writings: List<Writing>,
    val publications: List<Publication>,
    val stack: List<StackGroup>,
    val source: Link
)
