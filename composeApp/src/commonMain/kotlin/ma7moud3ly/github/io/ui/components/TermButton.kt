package ma7moud3ly.github.io.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

@Preview
@Composable
private fun TermButtonPreview() {
    TermPreview {
        Row {
            TermButton(label = "GITHUB", onClick = {})
            TermButton(label = "SELECTED", onClick = {}, selected = true)
        }
    }
}

/**
 * A `[ LABEL ]` button. It lights up, with its text in the background color,
 * while hovered or [selected].
 */
@Composable
fun TermButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    val phosphor = LocalPhosphor.current
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    val lit = selected || hovered
    TermText(
        text = "[ $label ]",
        color = if (lit) phosphor.background else phosphor.bright,
        glow = !lit,
        bold = selected,
        modifier = modifier
            .background(if (lit) phosphor.normal else Color.Transparent)
            .clickableItem(interaction, onClick)
            .padding(horizontal = 2.dp)
    )
}

/** Makes an element clickable with a hand cursor and no ripple, reporting hovers to [interaction]. */
fun Modifier.clickableItem(interaction: MutableInteractionSource, onClick: () -> Unit): Modifier = this
    .hoverable(interaction)
    .pointerHoverIcon(PointerIcon.Hand)
    .clickable(interactionSource = interaction, indication = null, onClick = onClick)
