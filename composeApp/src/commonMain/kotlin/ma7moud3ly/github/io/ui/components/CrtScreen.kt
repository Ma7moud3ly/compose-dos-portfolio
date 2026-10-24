package ma7moud3ly.github.io.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.ui.theme.AppTheme
import ma7moud3ly.github.io.ui.theme.Phosphor
import ma7moud3ly.github.io.ui.theme.Phosphors

/** Shows [content] on a [phosphor] screen, for IDE previews. */
@Composable
fun TermPreview(
    phosphor: Phosphor = Phosphors.Amber,
    content: @Composable () -> Unit
) {
    AppTheme(phosphor) {
        Box(
            modifier = Modifier
                .crtScreen(phosphor)
                .padding(16.dp)
        ) {
            content()
        }
    }
}

/**
 * Paints a CRT screen: a phosphor glow behind the content, then scanlines,
 * a slow scan beam and darkened corners over it.
 */
@Composable
fun Modifier.crtScreen(phosphor: Phosphor): Modifier {
    val transition = rememberInfiniteTransition()
    val beam by transition.animateFloat(
        initialValue = -0.2f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(tween(7000, easing = LinearEasing), RepeatMode.Restart)
    )
    return this
        .drawBehind {
            drawRect(phosphor.background)
            drawRect(
                Brush.radialGradient(
                    colors = listOf(phosphor.faint, Color.Transparent),
                    center = center,
                    radius = size.maxDimension * 0.7f
                )
            )
        }
        .drawWithContent {
            drawContent()
            val line = 3.dp.toPx()
            val dark = 1.dp.toPx()
            var y = 0f
            while (y < size.height) {
                drawRect(Color.Black.copy(alpha = 0.14f), Offset(0f, y), Size(size.width, dark))
                y += line
            }
            val beamHeight = 160.dp.toPx()
            val beamTop = beam * size.height - beamHeight / 2
            drawRect(
                Brush.verticalGradient(
                    colors = listOf(Color.Transparent, phosphor.bright.copy(alpha = 0.05f), Color.Transparent),
                    startY = beamTop,
                    endY = beamTop + beamHeight
                ),
                topLeft = Offset(0f, beamTop),
                size = Size(size.width, beamHeight)
            )
            drawRect(
                Brush.radialGradient(
                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.45f)),
                    center = center,
                    radius = size.maxDimension * 0.75f
                )
            )
        }
}
