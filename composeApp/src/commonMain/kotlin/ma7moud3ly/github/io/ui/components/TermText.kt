package ma7moud3ly.github.io.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

/** The size of the screen's text. Smaller on narrow screens. */
val LocalTermFontSize = staticCompositionLocalOf { 15.sp }

@Preview
@Composable
private fun TermTextPreview() {
    TermPreview {
        TermText("C:\\> hello, world")
    }
}

/**
 * Text in the screen's font, with a phosphor glow around it unless [glow]
 * is off. Text drawn on a lit background should turn the glow off.
 */
@Composable
fun TermText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalPhosphor.current.normal,
    fontSize: TextUnit = LocalTermFontSize.current,
    bold: Boolean = false,
    glow: Boolean = true,
    softWrap: Boolean = true
) {
    Text(
        text = text,
        modifier = modifier,
        softWrap = softWrap,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = color,
            fontSize = fontSize,
            lineHeight = fontSize * 1.5f,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            // a tight, faint glow: a wide one blurs the letters
            shadow = if (glow) Shadow(color = color.copy(alpha = 0.35f), blurRadius = 6f) else null
        )
    )
}

/** A block cursor that blinks like a DOS prompt. */
@Composable
fun BlinkingCursor(
    modifier: Modifier = Modifier,
    color: Color = LocalPhosphor.current.bright
) {
    var on by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(530)
            on = !on
        }
    }
    TermText("█", modifier, color = if (on) color else Color.Transparent)
}

/**
 * [text] typed out [step] characters at a time, every [delayMs]. Calls
 * [onDone] once all of it shows. IDE previews show the whole text at once.
 */
@Composable
fun TypedText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalPhosphor.current.normal,
    bold: Boolean = false,
    step: Int = 2,
    delayMs: Long = 12,
    onDone: () -> Unit = {}
) {
    val instant = LocalInspectionMode.current
    val done by rememberUpdatedState(onDone)
    var count by remember(text) { mutableIntStateOf(if (instant) text.length else 0) }
    LaunchedEffect(text) {
        while (count < text.length) {
            delay(delayMs)
            count = (count + step).coerceAtMost(text.length)
        }
        done()
    }
    TermText(text.take(count), modifier, color = color, bold = bold)
}

/**
 * How many of [total] lines to show, growing by one every [delayMs], for
 * output that prints line by line. IDE previews show all of them.
 */
@Composable
fun rememberReveal(total: Int, delayMs: Long = 30): Int {
    val instant = LocalInspectionMode.current
    var shown by remember(total) { mutableIntStateOf(if (instant) total else 0) }
    LaunchedEffect(total) {
        while (shown < total) {
            delay(delayMs)
            shown++
        }
    }
    return shown
}
