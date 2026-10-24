package ma7moud3ly.github.io.feature.portfolio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.components.TermText
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

@Preview(widthDp = 800)
@Composable
private fun StatusBarPreview() {
    TermPreview {
        StatusBar(compact = false)
    }
}

/** A lit bar with the keyboard hints, or a tap hint on [compact] screens. */
@Composable
fun StatusBar(compact: Boolean) {
    val phosphor = LocalPhosphor.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(phosphor.normal)
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        TermText(
            text = if (compact) "TAP A TAB OR A FILE"
            else "1-${Tab.entries.size} TAB  ←→ SWITCH  ↑↓ SELECT  ↵ OPEN  C COLOR",
            color = phosphor.background,
            glow = false,
            softWrap = false,
            modifier = Modifier.weight(1f)
        )
        TermText("READY", color = phosphor.background, glow = false, bold = true)
    }
}
