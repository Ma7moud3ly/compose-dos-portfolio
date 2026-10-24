package ma7moud3ly.github.io.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * The colors of a CRT screen, from its [bright] highlights down to the
 * [faint] glow behind the text.
 *
 * @param name the name shown on the color switch
 */
@Immutable
data class Phosphor(
    val name: String,
    val background: Color,
    val bright: Color,
    val normal: Color,
    val dim: Color,
    val faint: Color
)

/** The two classic monitor colors. [Amber] is the default. */
object Phosphors {
    val Amber = Phosphor(
        name = "AMBER",
        background = Color(0xFF080502),
        bright = Color(0xFFFFD68A),
        normal = Color(0xFFFFB520),
        dim = Color(0xFFC9974A),
        faint = Color(0xFF261900)
    )
    val Green = Phosphor(
        name = "GREEN",
        background = Color(0xFF030803),
        bright = Color(0xFF8CFFA8),
        normal = Color(0xFF4BEA74),
        dim = Color(0xFF5FAE74),
        faint = Color(0xFF0B2412)
    )

    /** The color the switch changes [phosphor] to. */
    fun other(phosphor: Phosphor): Phosphor = if (phosphor == Amber) Green else Amber
}
