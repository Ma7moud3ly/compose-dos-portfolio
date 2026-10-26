package ma7moud3ly.github.io.feature.portfolio.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ma7moud3ly.github.io.data.portfolio
import ma7moud3ly.github.io.feature.portfolio.components.CommandOutput
import ma7moud3ly.github.io.feature.portfolio.components.DetailsPanel
import ma7moud3ly.github.io.feature.portfolio.components.FileBrowser
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioEvent
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.model.Publication
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.components.TermText
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

@Preview(widthDp = 1000)
@Composable
private fun PublicationsTabPreview() {
    TermPreview {
        PublicationsTab(publications = portfolio.publications, selected = 0, compact = false, uiEvents = {})
    }
}

@Preview(widthDp = 500)
@Composable
private fun PublicationsTabPreviewEmpty() {
    TermPreview {
        PublicationsTab(publications = emptyList(), selected = 0, compact = true, uiEvents = {})
    }
}

/** The publications, or DOS's "File Not Found" while there are none. */
@Composable
fun PublicationsTab(
    publications: List<Publication>,
    selected: Int,
    compact: Boolean,
    uiEvents: (PortfolioEvent) -> Unit
) {
    CommandOutput(path = Tab.Publications.path, command = "dir") {
        if (publications.isEmpty()) {
            TermText("File Not Found", color = LocalPhosphor.current.bright, bold = true)
            return@CommandOutput
        }
        FileBrowser(
            items = publications,
            selected = selected,
            onSelect = { uiEvents(PortfolioEvent.Select(it)) },
            compact = compact,
            columns = "TITLE" to "YEAR",
            name = { it.title },
            note = { it.year }
        ) { publication ->
            DetailsPanel(
                title = publication.title,
                fields = listOf(
                    "Journal" to publication.venue,
                    "Year" to publication.year,
                    "Role" to publication.role
                ),
                text = "",
                links = listOf(publication.link),
                onOpen = { uiEvents(PortfolioEvent.OpenLink(it)) }
            )
        }
    }
}
