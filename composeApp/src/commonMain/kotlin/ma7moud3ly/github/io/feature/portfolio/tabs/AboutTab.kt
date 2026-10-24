package ma7moud3ly.github.io.feature.portfolio.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.data.portfolio
import ma7moud3ly.github.io.feature.portfolio.components.CommandOutput
import ma7moud3ly.github.io.feature.portfolio.components.LinkButtons
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioEvent
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.model.Profile
import ma7moud3ly.github.io.ui.components.BlinkingCursor
import ma7moud3ly.github.io.ui.components.BlockTitle
import ma7moud3ly.github.io.ui.components.Field
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.components.TermText
import ma7moud3ly.github.io.ui.components.TypedText
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

@Preview(widthDp = 1000)
@Composable
private fun AboutTabPreview() {
    TermPreview {
        AboutTab(profile = portfolio.profile, uiEvents = {})
    }
}

@Preview(widthDp = 360)
@Composable
private fun AboutTabPreviewCompact() {
    TermPreview {
        AboutTab(profile = portfolio.profile, uiEvents = {})
    }
}

/** The name in block letters, the job title, the summary, the facts and the main links. */
@Composable
fun AboutTab(profile: Profile, uiEvents: (PortfolioEvent) -> Unit) {
    val phosphor = LocalPhosphor.current
    CommandOutput(path = Tab.About.path, command = "type README.TXT") {
        Column {
            BlockTitle(profile.name)
            Spacer(Modifier.height(24.dp))
            TypedText(text = "> ${profile.title}", color = phosphor.bright, bold = true)
            TermText("  ${profile.tagline}", color = phosphor.dim)
            Spacer(Modifier.height(14.dp))
            TermText(profile.summary)
            Spacer(Modifier.height(20.dp))
            profile.facts.forEach { Field(it.label, it.value) }
            Spacer(Modifier.height(20.dp))
            LinkButtons(profile.links, onOpen = { uiEvents(PortfolioEvent.OpenLink(it)) })
            Spacer(Modifier.height(24.dp))
            Row {
                TermText("${Tab.About.path}> ", color = phosphor.dim)
                BlinkingCursor()
            }
        }
    }
}
