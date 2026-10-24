package ma7moud3ly.github.io.feature.portfolio.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ma7moud3ly.github.io.data.portfolio
import ma7moud3ly.github.io.feature.portfolio.components.CommandOutput
import ma7moud3ly.github.io.feature.portfolio.components.DetailsPanel
import ma7moud3ly.github.io.feature.portfolio.components.FileBrowser
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioEvent
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.model.Writing
import ma7moud3ly.github.io.ui.components.TermPreview

@Preview(widthDp = 1000)
@Composable
private fun BooksTabPreview() {
    TermPreview {
        BooksTab(writings = portfolio.writings, selected = 0, compact = false, uiEvents = {})
    }
}

/** The books and tutorials. */
@Composable
fun BooksTab(
    writings: List<Writing>,
    selected: Int,
    compact: Boolean,
    uiEvents: (PortfolioEvent) -> Unit
) {
    CommandOutput(path = Tab.Books.path, command = "dir") {
        FileBrowser(
            items = writings,
            selected = selected,
            onSelect = { uiEvents(PortfolioEvent.Select(it)) },
            compact = compact,
            columns = "TITLE" to "TYPE",
            name = { it.title },
            note = { it.kind.uppercase() }
        ) { writing ->
            DetailsPanel(
                title = writing.title,
                fields = listOfNotNull(
                    "Type" to writing.kind,
                    "Language" to writing.language,
                    writing.year?.let { "Year" to it }
                ),
                text = writing.description,
                links = writing.links,
                onOpen = { uiEvents(PortfolioEvent.OpenLink(it)) }
            )
        }
    }
}
