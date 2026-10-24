package ma7moud3ly.github.io.feature.portfolio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.components.TermText
import ma7moud3ly.github.io.ui.components.clickableItem
import ma7moud3ly.github.io.ui.components.rememberReveal
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

/** The width of the file list beside the details. */
private val ListWidth = 380.dp

@Preview(widthDp = 900)
@Composable
private fun FileBrowserPreview() {
    TermPreview {
        FileBrowser(
            items = listOf("Micro REPL", "Nemo Editor", "Quran Planet"),
            selected = 1,
            onSelect = {},
            compact = false,
            columns = "NAME" to "TYPE",
            name = { it },
            note = { "APP" }
        ) {
            TermText("Details of $it")
        }
    }
}

/**
 * A DOS-style file list beside the details of the picked item; on
 * [compact] screens the details go under the list. The list prints line
 * by line.
 *
 * @param columns the titles of the name and the note columns
 */
@Composable
fun <T> FileBrowser(
    items: List<T>,
    selected: Int,
    onSelect: (Int) -> Unit,
    compact: Boolean,
    columns: Pair<String, String>,
    name: (T) -> String,
    note: (T) -> String,
    details: @Composable (T) -> Unit
) {
    val item = items.getOrNull(selected) ?: return
    val list = @Composable {
        FileList(columns, items.map(name), items.map(note), selected, onSelect)
    }
    if (compact) {
        Column {
            list()
            Spacer(Modifier.height(16.dp))
            details(item)
        }
    } else {
        Row {
            Column(Modifier.width(ListWidth)) { list() }
            Spacer(Modifier.width(24.dp))
            Column(Modifier.weight(1f)) { details(item) }
        }
    }
}

/** The rows of [FileBrowser]: a name and a short note each, the picked row lit. */
@Composable
private fun FileList(
    columns: Pair<String, String>,
    names: List<String>,
    notes: List<String>,
    selected: Int,
    onSelect: (Int) -> Unit
) {
    val phosphor = LocalPhosphor.current
    // the header, then a line per item, then the footer
    val shown = rememberReveal(names.size + 2, delayMs = 40)
    Column {
        if (shown > 0) FileRowLayout(
            name = columns.first,
            note = columns.second,
            nameColor = phosphor.dim,
            noteColor = phosphor.dim,
            mark = " "
        )
        names.take((shown - 1).coerceAtLeast(0)).forEachIndexed { index, name ->
            FileRow(
                name = name,
                note = notes[index],
                lit = index == selected,
                onClick = { onSelect(index) }
            )
        }
        if (shown == names.size + 2) {
            Spacer(Modifier.height(4.dp))
            TermText(" ${names.size} file(s)      640K free", color = phosphor.dim, glow = false)
        }
    }
}

@Composable
private fun FileRow(name: String, note: String, lit: Boolean, onClick: () -> Unit) {
    val phosphor = LocalPhosphor.current
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    FileRowLayout(
        name = name,
        note = note,
        nameColor = if (lit) phosphor.background else phosphor.bright,
        noteColor = if (lit) phosphor.background else phosphor.dim,
        mark = if (lit) "►" else " ",
        bold = lit,
        glow = !lit,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                when {
                    lit -> phosphor.normal
                    hovered -> phosphor.faint
                    else -> Color.Transparent
                }
            )
            .clickableItem(interaction, onClick)
    )
}

/** One line of [FileList]: a [mark], the name filling the row and the note at the end. */
@Composable
private fun FileRowLayout(
    name: String,
    note: String,
    nameColor: Color,
    noteColor: Color,
    mark: String,
    modifier: Modifier = Modifier,
    bold: Boolean = false,
    glow: Boolean = true
) {
    Row(modifier = modifier.padding(vertical = 2.dp, horizontal = 4.dp)) {
        TermText("$mark ", color = nameColor, glow = glow)
        TermText(
            text = name,
            color = nameColor,
            bold = bold,
            glow = glow,
            softWrap = false,
            modifier = Modifier.weight(1f)
        )
        TermText(" $note", color = noteColor, glow = false, softWrap = false)
    }
}
