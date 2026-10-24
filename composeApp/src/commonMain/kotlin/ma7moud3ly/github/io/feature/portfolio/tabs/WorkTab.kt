package ma7moud3ly.github.io.feature.portfolio.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ma7moud3ly.github.io.data.portfolio
import ma7moud3ly.github.io.feature.portfolio.components.CommandOutput
import ma7moud3ly.github.io.feature.portfolio.components.DetailsPanel
import ma7moud3ly.github.io.feature.portfolio.components.FileBrowser
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioEvent
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.model.Job
import ma7moud3ly.github.io.ui.components.TermPreview

@Preview(widthDp = 1000)
@Composable
private fun WorkTabPreview() {
    TermPreview {
        WorkTab(jobs = portfolio.jobs, selected = 0, compact = false, uiEvents = {})
    }
}

@Preview(widthDp = 360)
@Composable
private fun WorkTabPreviewCompact() {
    TermPreview {
        WorkTab(jobs = portfolio.jobs, selected = 1, compact = true, uiEvents = {})
    }
}

/** The jobs, newest first, each with what was done there. */
@Composable
fun WorkTab(
    jobs: List<Job>,
    selected: Int,
    compact: Boolean,
    uiEvents: (PortfolioEvent) -> Unit
) {
    CommandOutput(path = Tab.Work.path, command = "dir") {
        FileBrowser(
            items = jobs,
            selected = selected,
            onSelect = { uiEvents(PortfolioEvent.Select(it)) },
            compact = compact,
            columns = "COMPANY" to "SINCE",
            name = { it.company },
            note = { it.period.substringBefore(" - ").takeLast(4) }
        ) { job ->
            DetailsPanel(
                title = job.company,
                fields = listOf(
                    "Role" to job.role,
                    "Where" to job.location,
                    "When" to job.period
                ),
                text = job.points.joinToString("\n") { "• $it" },
                links = job.links,
                onOpen = { uiEvents(PortfolioEvent.OpenLink(it)) }
            )
        }
    }
}
