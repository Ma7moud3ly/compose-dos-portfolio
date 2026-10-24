package ma7moud3ly.github.io.feature.portfolio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ma7moud3ly.github.io.feature.portfolio.model.Tab
import ma7moud3ly.github.io.ui.components.LocalTermFontSize
import ma7moud3ly.github.io.ui.components.TermPreview
import ma7moud3ly.github.io.ui.components.TermText
import ma7moud3ly.github.io.ui.components.clickableItem
import ma7moud3ly.github.io.ui.components.singleBorder
import ma7moud3ly.github.io.ui.theme.LocalPhosphor

@Preview(widthDp = 800)
@Composable
private fun TabBarPreview() {
    TermPreview {
        TabBar(selected = Tab.Apps, compact = false, onSelect = {})
    }
}

@Preview(widthDp = 360)
@Composable
private fun TabBarPreviewCompact() {
    TermPreview {
        TabBar(selected = Tab.About, compact = true, onSelect = {})
    }
}

/** A button for each tab, the open one lit. On [compact] screens the key caps are left out. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TabBar(selected: Tab, compact: Boolean, onSelect: (Tab) -> Unit) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(if (compact) 6.dp else 10.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Tab.entries.forEach { tab ->
            TabButton(
                tab = tab,
                selected = tab == selected,
                showKey = !compact,
                onClick = { onSelect(tab) }
            )
        }
    }
}

/**
 * A tab's name, after a small key cap with the number that opens it when
 * [showKey] is set. The button lights up while hovered or [selected].
 */
@Composable
private fun TabButton(tab: Tab, selected: Boolean, showKey: Boolean, onClick: () -> Unit) {
    val phosphor = LocalPhosphor.current
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    val lit = selected || hovered
    Row(
        modifier = Modifier
            .singleBorder(if (lit) phosphor.normal else phosphor.dim)
            .background(if (lit) phosphor.normal else Color.Transparent)
            .clickableItem(interaction, onClick)
            .padding(horizontal = 8.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showKey) {
            TermText(
                text = "${tab.number}",
                color = if (lit) phosphor.normal else phosphor.background,
                fontSize = LocalTermFontSize.current * 0.8f,
                bold = true,
                glow = false,
                modifier = Modifier
                    .background(if (lit) phosphor.background else phosphor.dim)
                    .padding(horizontal = 4.dp)
            )
            Spacer(Modifier.width(8.dp))
        }
        TermText(
            text = tab.label,
            color = if (lit) phosphor.background else phosphor.bright,
            bold = selected,
            glow = !lit
        )
    }
}
