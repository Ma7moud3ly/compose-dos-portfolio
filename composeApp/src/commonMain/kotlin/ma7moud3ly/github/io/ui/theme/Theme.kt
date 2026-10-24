package ma7moud3ly.github.io.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

/** The phosphor the surrounding screen is painted with. */
val LocalPhosphor = staticCompositionLocalOf { Phosphors.Amber }

/** Paints the page with [phosphor], in [appTypography]. */
@Composable
fun AppTheme(
    phosphor: Phosphor = Phosphors.Amber,
    content: @Composable () -> Unit
) {
    val colorScheme = darkColorScheme(
        primary = phosphor.normal,
        onPrimary = phosphor.background,
        background = phosphor.background,
        onBackground = phosphor.normal,
        surface = phosphor.background,
        onSurface = phosphor.normal
    )
    CompositionLocalProvider(LocalPhosphor provides phosphor) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = appTypography(),
            content = content
        )
    }
}
