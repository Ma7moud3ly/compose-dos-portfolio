package ma7moud3ly.github.io.ui.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

/** The width of a JetBrains Mono character, as a share of the font size, with a small margin. */
private const val CHAR_WIDTH = 0.62f

/** The smallest font size the letters are drawn at before words move to their own lines. */
private const val MIN_FONT_SIZE = 8f

/** The largest font size the letters are drawn at. */
private const val MAX_FONT_SIZE = 15f

/** The letters [BlockTitle] can draw, five rows each. */
private val Glyphs: Map<Char, List<String>> = mapOf(
    'A' to listOf(" ███ ", "█   █", "█████", "█   █", "█   █"),
    'D' to listOf("████ ", "█   █", "█   █", "█   █", "████ "),
    'H' to listOf("█   █", "█   █", "█████", "█   █", "█   █"),
    'L' to listOf("█    ", "█    ", "█    ", "█    ", "█████"),
    'M' to listOf("█   █", "██ ██", "█ █ █", "█   █", "█   █"),
    'N' to listOf("█   █", "██  █", "█ █ █", "█  ██", "█   █"),
    'O' to listOf(" ███ ", "█   █", "█   █", "█   █", " ███ "),
    'S' to listOf(" ████", "█    ", " ███ ", "    █", "████ "),
    'U' to listOf("█   █", "█   █", "█   █", "█   █", " ███ "),
    'Y' to listOf("█   █", " █ █ ", "  █  ", "  █  ", "  █  ")
)

@Preview
@Composable
private fun BlockTitlePreviewWide() {
    TermPreview {
        BlockTitle("Mahmoud Aly Hassan", Modifier.width(1100.dp))
    }
}

@Preview
@Composable
private fun BlockTitlePreviewNarrow() {
    TermPreview {
        BlockTitle("Mahmoud Aly Hassan", Modifier.width(340.dp))
    }
}

/** How many character columns [line] takes in block letters: five per letter and one between. */
private fun columnsOf(line: String): Int = line.length * 6 - 1

/** [line] in five rows of block letters. Letters with no glyph are left blank. */
private fun blockLetters(line: String): String = (0 until 5).joinToString("\n") { row ->
    line.map { Glyphs[it]?.get(row) ?: "     " }.joinToString(" ")
}

/**
 * Packs [words] into lines, as few as keep the letters at least
 * [MIN_FONT_SIZE] in [width] dp. A word wider than that gets a line of its own.
 */
private fun fitLines(words: List<String>, width: Float): List<String> {
    val maxColumns = (width / (MIN_FONT_SIZE * CHAR_WIDTH)).toInt()
    val lines = mutableListOf<String>()
    var line = ""
    words.forEach { word ->
        val longer = if (line.isEmpty()) word else "$line $word"
        if (line.isNotEmpty() && columnsOf(longer) > maxColumns) {
            lines += line
            line = word
        } else {
            line = longer
        }
    }
    if (line.isNotEmpty()) lines += line
    return lines
}

/**
 * [text] in big block letters with a phosphor gradient. The words wrap onto
 * as many lines as they need, and the letters are sized to fill the width,
 * up to [MAX_FONT_SIZE].
 */
@Composable
fun BlockTitle(text: String, modifier: Modifier = Modifier) {
    val phosphor = LocalPhosphor.current
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val lines = fitLines(text.uppercase().split(' '), maxWidth.value)
        val columns = lines.maxOf { columnsOf(it) }
        val fontSize = (maxWidth.value / (columns * CHAR_WIDTH)).coerceAtMost(MAX_FONT_SIZE).sp
        Text(
            text = lines.joinToString("\n\n") { blockLetters(it) },
            softWrap = false,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = fontSize,
                lineHeight = fontSize * 1.05f,
                brush = Brush.verticalGradient(listOf(phosphor.bright, phosphor.normal, phosphor.dim)),
                shadow = Shadow(color = phosphor.normal.copy(alpha = 0.6f), blurRadius = 14f)
            )
        )
    }
}
