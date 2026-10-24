package ma7moud3ly.github.io.feature.portfolio.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.ui.components.TermButton
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.components.TermText
import ma7moud3ly.github.io.ui.theme.LocalPhosphor
import ma7moud3ly.github.io.ui.theme.Phosphors

@Preview(widthDp = 600)
@Composable
private fun HeaderPreview() {
    TermPreview {
        Header(onOpenSource = {}, onTogglePhosphor = {})
    }
}

/** The system name on the left, then the source code button and the color switch on the right. */
@Composable
fun Header(onOpenSource: () -> Unit, onTogglePhosphor: () -> Unit) {
    val phosphor = LocalPhosphor.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TermText("▓▒░ MA-DOS 1.0", color = phosphor.bright, bold = true)
        TermText(" ░▒▓", color = phosphor.dim, modifier = Modifier.weight(1f))
        TermButton(label = "SOURCE", onClick = onOpenSource)
        Spacer(Modifier.width(10.dp))
        // the switch names the color it changes to
        TermButton(label = Phosphors.other(phosphor).name, onClick = onTogglePhosphor)
    }
}
