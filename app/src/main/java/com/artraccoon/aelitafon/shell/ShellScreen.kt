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
import com.artraccoon.aelitafon.apps.AppEntry
import com.artraccoon.aelitafon.apps.AppLauncher
import com.artraccoon.aelitafon.apps.AppRepository
import com.artraccoon.aelitafon.apps.AppSearchResult
import com.artraccoon.aelitafon.core.DefaultAelitaLocalCore
import com.artraccoon.aelitafon.device.CapabilityRegistry
import com.artraccoon.aelitafon.device.DeviceStateSnapshot
import com.artraccoon.aelitafon.device.DeviceStateReader
import com.artraccoon.aelitafon.logs.InMemoryActionLogStore
import com.artraccoon.aelitafon.evolution.LocalSuggestionEngine
import com.artraccoon.aelitafon.memory.MemoryRepository
import com.artraccoon.aelitafon.memory.MemoryEntry
import com.artraccoon.aelitafon.policy.DefaultPolicyEngine
import com.artraccoon.aelitafon.projects.ProjectEntry
import com.artraccoon.aelitafon.projects.ProjectRepository
import com.artraccoon.aelitafon.shell.components.AelitaHeader
import com.artraccoon.aelitafon.shell.components.CommandPanel
import com.artraccoon.aelitafon.shell.components.SystemNodeGrid
import com.artraccoon.aelitafon.systemagent.AelitaSystemAgent
import com.artraccoon.aelitafon.systemagent.DefaultAelitaSystemAgent
import com.artraccoon.aelitafon.ui.theme.AelitaFonTheme
import com.artraccoon.aelitafon.ui.theme.DeepBlack
import com.artraccoon.aelitafon.ui.theme.MutedViolet
import com.artraccoon.aelitafon.ui.theme.SoftViolet

@Composable
fun ShellScreen(
    systemAgent: AelitaSystemAgent,
    modifier: Modifier = Modifier,
) {
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
                    if (command.isEmpty()) {
                        shellState = shellState.copy(localStatus = "Команда пока пустая")
                    } else {
                        val result = systemAgent.handleCommand(command)
                        shellState = shellState.copy(
                            commandText = "",
                            localStatus = result.userMessage,
                        )
                    }
                },
                onVoiceClick = {
                    val result = systemAgent.openSection("Голос")
                    shellState = shellState.copy(
                        localStatus = "Голосовой режим будет добавлен позже. ${result.userMessage}",
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
                    text = "Локальные разделы Shell: память, проекты, журнал, предложения и честные системные ограничения.",
                    color = MutedViolet,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                )
                SystemNodeGrid(
                    nodes = shellState.systemNodes,
                    onNodeClick = { node ->
                        val result = systemAgent.openSection(node.title)
                        shellState = shellState.copy(
                            localStatus = result.userMessage,
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
        ShellScreen(
            systemAgent = DefaultAelitaSystemAgent(
                deviceStateReader = previewDeviceStateReader,
                capabilityRegistry = CapabilityRegistry(),
                policyEngine = DefaultPolicyEngine(),
                localCore = previewLocalCore,
                appRepository = previewAppRepository,
                appLauncher = previewAppLauncher,
                actionLogStore = previewActionLogStore,
            ),
        )
    }
}


private val previewDeviceStateReader = object : DeviceStateReader {
    override fun readSnapshot(): DeviceStateSnapshot = DeviceStateSnapshot(
        capturedAtMillis = 0L,
        packageName = "com.artraccoon.aelitafon.preview",
        appMode = "normal-app shell prototype",
        localOnly = true,
        privilegedAccess = false,
        notificationAccess = false,
        accessibilityAccess = false,
        rootAccess = false,
        romBridge = false,
    )
}


private val previewMemoryRepository = object : MemoryRepository {
    override fun addMemory(text: String): MemoryEntry = MemoryEntry("preview-memory", text, 0L)
    override fun getMemories(): List<MemoryEntry> = emptyList()
    override fun deleteMemory(id: String): Boolean = false
    override fun clearMemories(): Boolean = true
}

private val previewProjectRepository = object : ProjectRepository {
    override fun addProject(title: String): ProjectEntry = ProjectEntry("preview-project", title, null, 0L, 0L, "active")
    override fun getProjects(): List<ProjectEntry> = emptyList()
    override fun deleteProject(id: String): Boolean = false
    override fun clearProjects(): Boolean = true
}

private val previewActionLogStore = InMemoryActionLogStore()

private val previewAppRepository = object : AppRepository {
    override fun getLaunchableApps(): List<AppEntry> = listOf(
        AppEntry("com.example.calendar", "Calendar", "MainActivity", true),
    )

    override fun searchApps(query: String): AppSearchResult = AppSearchResult(
        query = query,
        matches = getLaunchableApps().filter { it.label.contains(query, ignoreCase = true) },
        userMessage = "Preview app search",
    )
}

private val previewAppLauncher = object : AppLauncher {
    override fun launchApp(packageName: String): Boolean = true
}

private val previewLocalCore = DefaultAelitaLocalCore(
    memoryRepository = previewMemoryRepository,
    projectRepository = previewProjectRepository,
    actionLogStore = previewActionLogStore,
    suggestionEngine = LocalSuggestionEngine(),
)
