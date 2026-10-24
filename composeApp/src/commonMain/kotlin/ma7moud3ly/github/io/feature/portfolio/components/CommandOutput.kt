package ma7moud3ly.github.io.feature.portfolio.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.model.Link
import ma7moud3ly.github.io.ui.components.Prompt
import ma7moud3ly.github.io.ui.components.TermButton
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.components.TermText

@Preview
@Composable
private fun CommandOutputPreview() {
    TermPreview {
        Column {
            CommandOutput(path = "C:\\ABOUT", command = "type README.TXT") {
                TermText("Hello")
            }
            LinkButtons(links = listOf(Link("GitHub", ""), Link("LinkedIn", "")), onOpen = {})
        }
    }
}

/**
 * Types [command] after [path], then shows [content] once it is typed, as
 * if the command printed it. IDE previews show the content at once.
 */
@Composable
fun CommandOutput(path: String, command: String, content: @Composable () -> Unit) {
    var ready by remember { mutableStateOf(false) }
    val instant = LocalInspectionMode.current
    Prompt(path = path, command = command, onDone = { ready = true })
    if (ready || instant) {
        Spacer(Modifier.height(16.dp))
        content()
    }
}

/** A `[ LABEL ]` button for each link, calling [onOpen] with it. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LinkButtons(links: List<Link>, onOpen: (Link) -> Unit) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        links.forEach { link ->
            TermButton(label = link.label.uppercase(), onClick = { onOpen(link) })
        }
    }
}
