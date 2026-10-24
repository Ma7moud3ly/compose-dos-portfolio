package ma7moud3ly.github.io.feature.portfolio.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ma7moud3ly.github.io.data.portfolio
import ma7moud3ly.github.io.feature.portfolio.components.CommandOutput
import ma7moud3ly.github.io.feature.portfolio.components.DetailsPanel
import ma7moud3ly.github.io.feature.portfolio.components.FileBrowser
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioEvent
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.model.Project
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.theme.Phosphors

@Preview(widthDp = 1000)
@Composable
private fun AppsTabPreview() {
    TermPreview {
        AppsTab(projects = portfolio.projects, selected = 2, compact = false, uiEvents = {})
    }
}

@Preview(widthDp = 360)
@Composable
private fun AppsTabPreviewCompactAmber() {
    TermPreview(Phosphors.Amber) {
        AppsTab(projects = portfolio.projects, selected = 0, compact = true, uiEvents = {})
    }
}

/** The apps and libraries. */
@Composable
fun AppsTab(
    projects: List<Project>,
    selected: Int,
    compact: Boolean,
    uiEvents: (PortfolioEvent) -> Unit
) {
    CommandOutput(path = Tab.Apps.path, command = "dir") {
        FileBrowser(
            items = projects,
            selected = selected,
            onSelect = { uiEvents(PortfolioEvent.Select(it)) },
            compact = compact,
            columns = "NAME" to "TYPE",
            name = { it.name },
            note = { if (it.isLibrary) "LIB" else "APP" }
        ) { project ->
            DetailsPanel(
                title = project.name,
                fields = listOf(
                    "Runs on" to project.platforms.joinToString(" · "),
                    "Built with" to project.tags.joinToString(", ")
                ),
                text = project.description,
                links = project.links,
                onOpen = { uiEvents(PortfolioEvent.OpenLink(it)) }
            )
        }
    }
}
