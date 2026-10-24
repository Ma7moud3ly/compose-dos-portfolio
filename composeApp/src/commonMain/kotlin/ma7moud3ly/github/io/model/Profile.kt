package ma7moud3ly.github.io.model

/** A labeled fact, such as years of experience or a degree. */
data class Fact(val label: String, val value: String)

/**
 * Who the portfolio is about.
 *
 * @param tagline a short line under the [title]
 */
data class Profile(
    val name: String,
    val title: String,
    val tagline: String,
    val summary: String,
    val facts: List<Fact>,
    val links: List<Link>
)
