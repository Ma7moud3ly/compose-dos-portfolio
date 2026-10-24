package ma7moud3ly.github.io.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

@Preview
@Composable
private fun TermWindowPreview() {
    TermPreview {
        TermWindow(title = "C:\\APPS", modifier = Modifier.size(320.dp, 160.dp)) {
            TermText("Hello from the window")
        }
    }
}

/** A double-framed window with its [title] set into the top line. */
@Composable
fun TermWindow(
    title: String,
    modifier: Modifier = Modifier,
    contentPadding: Dp = 20.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val phosphor = LocalPhosphor.current
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .doubleBorder(phosphor.normal)
                .padding(horizontal = contentPadding, vertical = contentPadding + 4.dp),
            content = content
        )
        TermText(
            text = " $title ",
            color = phosphor.bright,
            bold = true,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .background(phosphor.background)
        )
    }
}

/** A glowing double-line frame, like the boxes DOS programs drew with `╔═╗`. */
fun Modifier.doubleBorder(color: Color, width: Dp = 1.5.dp, gap: Dp = 3.dp): Modifier = drawBehind {
    val w = width.toPx()
    val inset = w + gap.toPx()
    fun frame(offset: Float, stroke: Float, tint: Color) = drawRect(
        color = tint,
        topLeft = Offset(offset, offset),
        size = Size(size.width - 2 * offset, size.height - 2 * offset),
        style = Stroke(stroke)
    )
    // a wide, faint pass under each line makes it glow
    frame(w / 2, w * 5, color.copy(alpha = 0.12f))
    frame(inset + w / 2, w * 5, color.copy(alpha = 0.12f))
    frame(w / 2, w, color)
    frame(inset + w / 2, w, color)
}

/** A thin single-line frame. */
fun Modifier.singleBorder(color: Color, width: Dp = 1.dp): Modifier = drawBehind {
    val w = width.toPx()
    drawRect(
        color = color,
        topLeft = Offset(w / 2, w / 2),
        size = Size(size.width - w, size.height - w),
        style = Stroke(w)
    )
}
