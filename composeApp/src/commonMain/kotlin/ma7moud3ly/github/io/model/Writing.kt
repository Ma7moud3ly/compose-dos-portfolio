package ma7moud3ly.github.io.model

/**
 * A book, tutorial or course.
 *
 * @param kind what it is, such as "Book" or "Course"
 * @param language the languages it is written in
 * @param year when it was published, or null to leave it out
 */
data class Writing(
    val title: String,
    val kind: String,
    val language: String,
    val description: String,
    val links: List<Link>,
    val year: String? = null
)
