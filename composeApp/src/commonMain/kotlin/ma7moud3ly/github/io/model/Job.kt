package ma7moud3ly.github.io.model

/**
 * A job.
 *
 * @param period when it started and ended, such as "Dec 2023 - Present"
 * @param points what was done there, one line each
 */
data class Job(
    val company: String,
    val role: String,
    val location: String,
    val period: String,
    val points: List<String>,
    val links: List<Link>
)
