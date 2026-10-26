package ma7moud3ly.github.io.feature.portfolio

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ma7moud3ly.github.io.data.portfolio
import ma7moud3ly.github.io.feature.portfolio.boot.BootContent
import ma7moud3ly.github.io.feature.portfolio.components.Header
import ma7moud3ly.github.io.feature.portfolio.components.StatusBar
import ma7moud3ly.github.io.feature.portfolio.components.TabBar
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioEvent
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioState
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.feature.portfolio.tabs.AboutTab
import ma7moud3ly.github.io.feature.portfolio.tabs.AppsTab
import ma7moud3ly.github.io.feature.portfolio.tabs.BooksTab
import ma7moud3ly.github.io.feature.portfolio.tabs.PublicationsTab
import ma7moud3ly.github.io.feature.portfolio.tabs.StackTab
import ma7moud3ly.github.io.model.Portfolio
import ma7moud3ly.github.io.ui.components.LocalTermFontSize
import ma7moud3ly.github.io.ui.components.LocalTermGlow
import ma7moud3ly.github.io.ui.components.TermWindow
import ma7moud3ly.github.io.ui.components.crtScreen
import ma7moud3ly.github.io.ui.theme.AppTheme
import ma7moud3ly.github.io.ui.theme.LocalPhosphor
import ma7moud3ly.github.io.ui.theme.Phosphor
import ma7moud3ly.github.io.ui.theme.Phosphors

/** The widest the screen content grows to. */
private val MaxContentWidth = 1200.dp

/** A window narrower than this gets the compact screen. */
private val CompactWidth = 720.dp

@Preview(widthDp = 1280, heightDp = 800)
@Composable
private fun PortfolioScreenPreview() {
    ScreenPreview(Phosphors.Green, PortfolioState(booted = true))
}

@Preview(widthDp = 1280, heightDp = 800)
@Composable
private fun PortfolioScreenPreviewApps() {
    ScreenPreview(Phosphors.Amber, PortfolioState(booted = true, tab = Tab.Apps))
}

@Preview(widthDp = 400, heightDp = 860)
@Composable
private fun PortfolioScreenPreviewCompact() {
    ScreenPreview(Phosphors.Green, PortfolioState(booted = true, tab = Tab.Stack))
}

@Preview(widthDp = 800, heightDp = 500)
@Composable
private fun PortfolioScreenPreviewBoot() {
    ScreenPreview(Phosphors.Green, PortfolioState(booted = false))
}

@Composable
private fun ScreenPreview(phosphor: Phosphor, state: PortfolioState) {
    AppTheme(phosphor) {
        PortfolioScreenContent(portfolio = portfolio, state = state, uiEvents = {})
    }
}

/**
 * The portfolio as a DOS screen: the CRT switching on, then a header, the tabs, a
 * window with the open tab and a status bar.
 *
 * The screen takes the keyboard focus; each key press goes to [uiEvents]
 * as the event it stands for. Below [CompactWidth] the text is smaller and
 * the lists stack over their details.
 */
@Composable
fun PortfolioScreenContent(
    portfolio: Portfolio,
    state: PortfolioState,
    uiEvents: (PortfolioEvent) -> Unit
) {
    val phosphor = LocalPhosphor.current
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) { focusRequester.requestFocus() }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .crtScreen(phosphor)
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                val uiEvent = event.toPortfolioEvent(state.booted) ?: return@onPreviewKeyEvent false
                uiEvents(uiEvent)
                true
            }
    ) {
        val compact = maxWidth < CompactWidth
        val gutter = if (compact) 10.dp else 28.dp
        CompositionLocalProvider(
            LocalTermFontSize provides if (compact) 14.sp else 15.sp,
            LocalTermGlow provides if (compact) 0.5f else 1f
        ) {
            if (state.booted.not()) BootContent(
                onFinished = { uiEvents(PortfolioEvent.FinishBoot) }
            ) else Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = gutter, vertical = if (compact) 10.dp else 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .widthIn(max = MaxContentWidth)
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Header(
                        onOpenSource = { uiEvents(PortfolioEvent.OpenLink(portfolio.source)) },
                        onTogglePhosphor = { uiEvents(PortfolioEvent.TogglePhosphor) }
                    )
                    Spacer(Modifier.height(12.dp))
                    TabBar(
                        selected = state.tab,
                        compact = compact,
                        onSelect = { uiEvents(PortfolioEvent.OpenTab(it)) }
                    )
                    Spacer(Modifier.height(16.dp))
                    TermWindow(
                        title = state.tab.path,
                        contentPadding = if (compact) 12.dp else 22.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        // a new tab starts fresh: scrolled to the top, its prompt typed again
                        key(state.tab) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                            ) {
                                TabContent(
                                    portfolio = portfolio,
                                    state = state,
                                    compact = compact,
                                    uiEvents = uiEvents
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    StatusBar(compact = compact)
                }
            }
        }
    }
}

/** The content of the open tab. */
@Composable
private fun TabContent(
    portfolio: Portfolio,
    state: PortfolioState,
    compact: Boolean,
    uiEvents: (PortfolioEvent) -> Unit
) {
    val selected = state.selected(state.tab)
    when (state.tab) {
        Tab.About -> AboutTab(
            profile = portfolio.profile,
            uiEvents = uiEvents
        )

        Tab.Stack -> StackTab(groups = portfolio.stack)

        Tab.Apps -> AppsTab(
            projects = portfolio.projects,
            selected = selected,
            compact = compact,
            uiEvents = uiEvents
        )

        Tab.Books -> BooksTab(
            writings = portfolio.writings,
            selected = selected,
            compact = compact,
            uiEvents = uiEvents
        )

        Tab.Publications -> PublicationsTab(
            publications = portfolio.publications,
            selected = selected,
            compact = compact,
            uiEvents = uiEvents
        )
    }
}
