package com.artraccoon.aelitafon.shell.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artraccoon.aelitafon.shell.SystemNode

@Composable
fun SystemNodeGrid(
    nodes: List<SystemNode>,
    onNodeClick: (SystemNode) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        nodes.chunked(2).forEach { rowNodes ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                rowNodes.forEach { node ->
                    SystemNodeCard(
                        node = node,
                        onClick = { onNodeClick(node) },
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowNodes.size == 1) {
                    Column(modifier = Modifier.weight(1f)) {}
                }
            }
        }
    }
}
