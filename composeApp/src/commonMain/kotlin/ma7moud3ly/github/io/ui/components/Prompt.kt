package ma7moud3ly.github.io.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

@Preview
@Composable
private fun PromptPreview() {
    TermPreview {
        Column {
            Prompt(path = "C:\\APPS", command = "dir")
            Field(label = "Location", value = "Cairo, Egypt")
        }
    }
}

/** A command typed after a `C:\>` style [path], then [onDone] once it is typed. */
@Composable
fun Prompt(path: String, command: String, onDone: () -> Unit = {}) {
    val phosphor = LocalPhosphor.current
    Row {
        TermText("$path> ", color = phosphor.dim)
        TypedText(command, color = phosphor.bright, step = 1, delayMs = 45, onDone = onDone)
    }
}

/** A dotted label and its value: `LOCATION.... Egypt`. */
@Composable
fun Field(label: String, value: String, modifier: Modifier = Modifier) {
    val phosphor = LocalPhosphor.current
    Row(modifier = modifier) {
        TermText(label.uppercase().padEnd(12, '.') + " ", color = phosphor.dim)
        TermText(value, color = phosphor.bright, modifier = Modifier.weight(1f, fill = false))
    }
}
