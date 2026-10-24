package ma7moud3ly.github.io.feature.portfolio.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import ma7moud3ly.github.io.data.portfolio
import ma7moud3ly.github.io.feature.portfolio.components.CommandOutput
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.model.StackGroup
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.components.TermText
import ma7moud3ly.github.io.ui.components.rememberReveal
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

@Preview(widthDp = 600, heightDp = 1400)
@Composable
private fun StackTabPreview() {
    TermPreview {
        StackTab(groups = portfolio.stack)
    }
}

/**
 * The lines of a `tree` of [groups], each paired with whether it is a
 * folder: the root, then each group with its skills under it.
 */
private fun treeLines(groups: List<StackGroup>): List<Pair<String, Boolean>> = buildList {
    add(Tab.Stack.path to true)
    groups.forEachIndexed { g, group ->
        val lastGroup = g == groups.lastIndex
        add((if (lastGroup) "└── " else "├── ") + group.title.uppercase() to true)
        val branch = if (lastGroup) "    " else "│   "
        group.skills.forEachIndexed { s, skill ->
            add(branch + (if (s == group.skills.lastIndex) "└── " else "├── ") + skill to false)
        }
    }
}

/** The skills as a `tree` of folders, printed line by line. */
@Composable
fun StackTab(groups: List<StackGroup>) {
    val phosphor = LocalPhosphor.current
    val lines = remember(groups) { treeLines(groups) }
    CommandOutput(path = Tab.Stack.path, command = "tree") {
        val shown = rememberReveal(lines.size, delayMs = 25)
        Column {
            lines.take(shown).forEach { (line, folder) ->
                TermText(
                    text = line,
                    color = if (folder) phosphor.bright else phosphor.normal,
                    bold = folder,
                    softWrap = false
                )
            }
        }
    }
}
