package ma7moud3ly.github.io.feature.portfolio.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.model.Link
import ma7moud3ly.github.io.ui.components.Field
import ma7moud3ly.github.io.ui.components.LocalTermFontSize
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.components.TermText
import ma7moud3ly.github.io.ui.components.TypedText
import ma7moud3ly.github.io.ui.components.singleBorder
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

@Preview(widthDp = 500)
@Composable
private fun DetailsPanelPreview() {
    TermPreview {
        DetailsPanel(
            title = "Micro REPL",
            fields = listOf("Runs on" to "Android · Desktop · Web"),
            text = "A MicroPython IDE over USB serial.",
            links = listOf(Link("GitHub", "")),
            onOpen = {}
        )
    }
}

/**
 * A framed panel with a big [title], dotted [fields], typed [text] and
 * [links]. Empty text and links are left out.
 */
@Composable
fun DetailsPanel(
    title: String,
    fields: List<Pair<String, String>>,
    text: String,
    links: List<Link>,
    onOpen: (Link) -> Unit
) {
    val phosphor = LocalPhosphor.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .singleBorder(phosphor.dim)
            .padding(16.dp)
    ) {
        TermText(
            text = title,
            color = phosphor.bright,
            bold = true,
            fontSize = LocalTermFontSize.current * 1.4f
        )
        Spacer(Modifier.height(10.dp))
        fields.forEach { (label, value) -> Field(label, value) }
        if (text.isNotEmpty()) {
            Spacer(Modifier.height(14.dp))
            TypedText(text)
        }
        if (links.isNotEmpty()) {
            Spacer(Modifier.height(18.dp))
            LinkButtons(links, onOpen)
        }
    }
}
