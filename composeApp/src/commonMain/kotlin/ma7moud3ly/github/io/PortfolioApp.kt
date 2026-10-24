package ma7moud3ly.github.io

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import ma7moud3ly.github.io.feature.portfolio.PortfolioScreen
import ma7moud3ly.github.io.feature.portfolio.PortfolioViewModel
import ma7moud3ly.github.io.ui.theme.AppTheme

/** The app's root: the portfolio screen, in the color the visitor last picked. */
@Composable
fun PortfolioApp(
    viewModel: PortfolioViewModel = viewModel { PortfolioViewModel() }
) {
    AppTheme(phosphor = viewModel.phosphor) {
        PortfolioScreen(viewModel = viewModel)
    }
}
