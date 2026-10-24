package ma7moud3ly.github.io.feature.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.viewmodel.compose.viewModel
import ma7moud3ly.github.io.feature.portfolio.model.PortfolioEvent

@Composable
fun PortfolioScreen(
    viewModel: PortfolioViewModel = viewModel { PortfolioViewModel() }
) {
    val uriHandler = LocalUriHandler.current

    PortfolioScreenContent(
        portfolio = viewModel.portfolio,
        state = viewModel.state,
        uiEvents = {
            when (it) {
                is PortfolioEvent.OpenLink -> uriHandler.openUri(it.link.url)
                is PortfolioEvent.OpenSelected -> viewModel.selectedLinks()
                    .firstOrNull()
                    ?.let { link -> uriHandler.openUri(link.url) }

                else -> viewModel.onEvent(it)
            }
        }
    )
}
