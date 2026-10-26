package ma7moud3ly.github.io.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

/** The smallest a pixel of a letter gets before words move to their own lines, in dp. */
private const val MIN_PIXEL = 5f

/** The largest a pixel of a letter gets, in dp. */
private const val MAX_PIXEL = 11f

/** Rows of empty pixels between two lines of letters. */
private const val LINE_GAP = 2

/** The rows each letter is drawn in. */
private const val LETTER_ROWS = 5

/** The letters [BlockTitle] can draw, five rows of five pixels each; `█` is a lit pixel. */
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
        BlockTitle("Mahmoud Aly", Modifier.width(1100.dp))
    }
}

@Preview
@Composable
private fun BlockTitlePreviewNarrow() {
    TermPreview {
        BlockTitle("Mahmoud Aly", Modifier.width(300.dp))
    }
}

/** How many pixel columns [line] takes: five per letter and one between. */
private fun columnsOf(line: String): Int = line.length * 6 - 1

/**
 * Packs [words] into lines, as few as keep a pixel at least [MIN_PIXEL] dp
 * in [width] dp. A word wider than that gets a line of its own.
 */
private fun fitLines(words: List<String>, width: Float): List<String> {
    val maxColumns = (width / MIN_PIXEL).toInt()
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
 * [text] in big pixel letters with a phosphor gradient and glow. The words
 * wrap onto as many lines as they need, and the pixels are sized to fill
 * the width, up to [MAX_PIXEL] dp.
 */
@Composable
fun BlockTitle(text: String, modifier: Modifier = Modifier) {
    val phosphor = LocalPhosphor.current
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val lines = fitLines(text.uppercase().split(' '), maxWidth.value)
        val columns = lines.maxOf { columnsOf(it) }
        val pixel = (maxWidth / columns).coerceAtMost(MAX_PIXEL.dp)
        val rows = lines.size * LETTER_ROWS + (lines.size - 1) * LINE_GAP
        Canvas(Modifier.size(pixel * columns, pixel * rows)) {
            val p = pixel.toPx()
            // a thin dark seam between pixels, like the dots of a screen
            val seam = p * 0.12f
            val glow = p * 0.35f
            lines.forEachIndexed { index, line ->
                val top = index * (LETTER_ROWS + LINE_GAP) * p
                val brush = Brush.verticalGradient(
                    colors = listOf(phosphor.bright, phosphor.normal, phosphor.dim),
                    startY = top,
                    endY = top + LETTER_ROWS * p
                )
                line.forEachIndexed { letter, char ->
                    val glyph = Glyphs[char] ?: return@forEachIndexed
                    glyph.forEachIndexed { row, pixels ->
                        pixels.forEachIndexed { column, lit ->
                            if (lit != '█') return@forEachIndexed
                            val x = (letter * 6 + column) * p
                            val y = top + row * p
                            drawRect(
                                color = phosphor.normal.copy(alpha = 0.15f),
                                topLeft = Offset(x - glow, y - glow),
                                size = Size(p + 2 * glow, p + 2 * glow)
                            )
                            drawRect(
                                brush = brush,
                                topLeft = Offset(x + seam / 2, y + seam / 2),
                                size = Size(p - seam, p - seam)
                            )
                        }
                    }
                }
            }
        }
    }
}
