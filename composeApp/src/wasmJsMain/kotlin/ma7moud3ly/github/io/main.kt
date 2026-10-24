package ma7moud3ly.github.io

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    // the spinner index.html shows while the app loads
    document.getElementById("loader")?.remove()
    ComposeViewport {
        PortfolioApp()
    }
}
