package ma7moud3ly.github.io.feature.portfolio.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.data.portfolio
import ma7moud3ly.github.io.feature.portfolio.components.CommandOutput
import ma7moud3ly.github.io.feature.portfolio.components.DetailsPanel
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioEvent
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.model.Link
import ma7moud3ly.github.io.model.Publication
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.components.TermText
import ma7moud3ly.github.io.ui.components.rememberReveal
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

@Preview(widthDp = 1000)
@Composable
private fun PublicationsTabPreview() {
    TermPreview {
        PublicationsTab(publications = portfolio.publications, uiEvents = {})
    }
}

@Preview(widthDp = 500)
@Composable
private fun PublicationsTabPreviewEmpty() {
    TermPreview {
        PublicationsTab(publications = emptyList(), uiEvents = {})
    }
}

/** The publications, or DOS's "File Not Found" while there are none. */
@Composable
fun PublicationsTab(publications: List<Publication>, uiEvents: (PortfolioEvent) -> Unit) {
    val phosphor = LocalPhosphor.current
    CommandOutput(path = Tab.Publications.path, command = "dir") {
        Column {
            TermText(" Volume in drive C is PORTFOLIO", color = phosphor.dim)
            TermText(" Directory of ${Tab.Publications.path}", color = phosphor.dim)
            Spacer(Modifier.height(16.dp))
            if (publications.isEmpty()) {
                TermText("File Not Found", color = phosphor.bright, bold = true)
            } else {
                val shown = rememberReveal(publications.size, delayMs = 60)
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    publications.take(shown).forEach { publication ->
                        DetailsPanel(
                            title = publication.title,
                            fields = listOf(
                                "Journal" to publication.venue,
                                "Year" to publication.year,
                                "Role" to publication.role
                            ),
                            text = "",
                            links = listOf(Link("Read paper", publication.url)),
                            onOpen = { uiEvents(PortfolioEvent.OpenLink(it)) }
                        )
                    }
                }
            }
        }
    }
}
