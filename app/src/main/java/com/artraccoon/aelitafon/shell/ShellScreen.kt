package com.artraccoon.aelitafon.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artraccoon.aelitafon.shell.components.AelitaHeader
import com.artraccoon.aelitafon.shell.components.CommandPanel
import com.artraccoon.aelitafon.shell.components.SystemNodeGrid
import com.artraccoon.aelitafon.ui.theme.AelitaFonTheme
import com.artraccoon.aelitafon.ui.theme.DeepBlack
import com.artraccoon.aelitafon.ui.theme.MutedViolet
import com.artraccoon.aelitafon.ui.theme.SoftViolet

@Composable
fun ShellScreen(modifier: Modifier = Modifier) {
    var shellState by remember { mutableStateOf(ShellState()) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = DeepBlack,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepBlack)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 30.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            AelitaHeader(
                modifier = Modifier.fillMaxWidth(),
            )

            CommandPanel(
                commandText = shellState.commandText,
                localStatus = shellState.localStatus,
                onCommandChange = { command ->
                    shellState = shellState.copy(commandText = command)
                },
                onSendCommand = {
                    val command = shellState.commandText.trim()
                    shellState = shellState.copy(
                        localStatus = if (command.isEmpty()) {
                            "Команда пока пустая"
                        } else {
                            "Команда принята локально: $command"
                        },
                    )
                },
                onVoiceClick = {
                    shellState = shellState.copy(
                        localStatus = "Голосовой режим будет добавлен позже",
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Системные узлы",
                    color = SoftViolet,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "Нефункциональные точки будущей оболочки. Реальные модули будут подключены позже.",
                    color = MutedViolet,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                )
                SystemNodeGrid(
                    nodes = shellState.systemNodes,
                    onNodeClick = { node ->
                        shellState = shellState.copy(
                            localStatus = "Раздел пока готовится: ${node.title}",
                        )
                    },
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShellScreenPreview() {
    AelitaFonTheme {
        ShellScreen()
    }
}
