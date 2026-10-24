package ma7moud3ly.github.io.model

/**
 * An app or library.
 *
 * @param platforms where it runs, such as "Android" or "Web"
 * @param tags the main technologies it is built with
 */
data class Project(
    val name: String,
    val description: String,
    val platforms: List<String>,
    val tags: List<String>,
    val links: List<Link>
) {
    /** Whether it is a library, marked by a "Library" tag. */
    val isLibrary: Boolean get() = "Library" in tags
}
