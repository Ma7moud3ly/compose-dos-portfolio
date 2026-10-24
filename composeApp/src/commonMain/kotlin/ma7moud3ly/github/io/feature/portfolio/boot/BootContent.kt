package ma7moud3ly.github.io.feature.portfolio.boot

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.theme.LocalPhosphor
import ma7moud3ly.github.io.ui.theme.Phosphor

/** How long the whole start-up takes, in milliseconds. */
private const val BOOT_MS = 1600

/** The share of [BOOT_MS] the static noise fills before the screen powers on. */
private const val NOISE_SHARE = 0.6f

/** The most noise specks drawn in one frame. */
private const val MAX_SPECKS = 5000

@Preview(widthDp = 800, heightDp = 500)
@Composable
private fun BootContentPreview() {
    TermPreview {
        BootContent(onFinished = {}, modifier = Modifier.size(800.dp, 500.dp))
    }
}

/**
 * A CRT switching on: static noise, then a bright line in the middle that
 * opens up into the whole screen. Calls [onFinished] when it is done, or as
 * soon as the screen is tapped.
 */
@Composable
fun BootContent(onFinished: () -> Unit, modifier: Modifier = Modifier) {
    val phosphor = LocalPhosphor.current
    val finished by rememberUpdatedState(onFinished)
    val instant = LocalInspectionMode.current
    // a new value each frame draws a new pattern of noise
    var frame by remember { mutableIntStateOf(0) }
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        if (instant) return@LaunchedEffect
        launch {
            while (isActive) withFrameNanos { frame++ }
        }
        progress.animateTo(1f, tween(BOOT_MS, easing = LinearEasing))
        finished()
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) { detectTapGestures { finished() } }
    ) {
        val time = progress.value
        if (time < NOISE_SHARE) {
            drawNoise(seed = frame, phosphor = phosphor, alpha = 1f)
        } else {
            val powerOn = (time - NOISE_SHARE) / (1f - NOISE_SHARE)
            drawNoise(seed = frame, phosphor = phosphor, alpha = 1f - powerOn)
            drawPowerOn(progress = powerOn, phosphor = phosphor)
        }
    }
}

/**
 * Static noise: specks of random brightness and width scattered over the
 * screen, with a few faint bands rolling down it. [seed] picks the pattern.
 */
private fun DrawScope.drawNoise(seed: Int, phosphor: Phosphor, alpha: Float) {
    if (alpha <= 0f) return
    val random = Random(seed)
    val speck = 3.dp.toPx()
    val specks = (size.width * size.height / (speck * speck * 8)).toInt().coerceAtMost(MAX_SPECKS)
    repeat(specks) {
        val brightness = random.nextFloat()
        drawRect(
            color = (if (brightness > 0.85f) phosphor.bright else phosphor.dim)
                .copy(alpha = alpha * brightness * 0.8f),
            topLeft = Offset(random.nextFloat() * size.width, random.nextFloat() * size.height),
            size = Size(speck * (1 + random.nextInt(3)), speck)
        )
    }
    val band = 24.dp.toPx()
    repeat(3) { index ->
        val top = (seed * 9 + index * size.height / 3) % size.height
        drawRect(
            color = phosphor.normal.copy(alpha = alpha * 0.07f),
            topLeft = Offset(0f, top),
            size = Size(size.width, band)
        )
    }
}

/**
 * The picture coming on: a thin bright line across the middle grows to the
 * full height while it fades, as [progress] goes from 0 to 1.
 */
private fun DrawScope.drawPowerOn(progress: Float, phosphor: Phosphor) {
    // eases out: the line opens fast, then settles
    val open = 1f - (1f - progress) * (1f - progress)
    val height = (2.dp.toPx() + (size.height - 2.dp.toPx()) * open)
    val top = (size.height - height) / 2
    val fade = 1f - progress
    drawRect(
        brush = Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                phosphor.bright.copy(alpha = fade),
                Color.Transparent
            ),
            startY = top,
            endY = top + height
        ),
        topLeft = Offset(0f, top),
        size = Size(size.width, height)
    )
}
