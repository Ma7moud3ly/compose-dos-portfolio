package ma7moud3ly.github.io.model

/**
 * A published paper.
 *
 * @param role the author's part, such as "First author"
 */
data class Publication(
    val title: String,
    val venue: String,
    val year: String,
    val role: String,
    val url: String
)
