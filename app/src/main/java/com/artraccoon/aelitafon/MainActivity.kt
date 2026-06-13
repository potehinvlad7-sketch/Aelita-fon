package com.artraccoon.aelitafon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.artraccoon.aelitafon.device.CapabilityRegistry
import com.artraccoon.aelitafon.device.NormalAppDeviceStateReader
import com.artraccoon.aelitafon.logs.InMemoryActionLogStore
import com.artraccoon.aelitafon.policy.DefaultPolicyEngine
import com.artraccoon.aelitafon.shell.ShellScreen
import com.artraccoon.aelitafon.systemagent.DefaultAelitaSystemAgent
import com.artraccoon.aelitafon.ui.theme.AelitaFonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val systemAgent = DefaultAelitaSystemAgent(
            deviceStateReader = NormalAppDeviceStateReader(applicationContext),
            capabilityRegistry = CapabilityRegistry(),
            policyEngine = DefaultPolicyEngine(),
            actionLogStore = InMemoryActionLogStore(),
        )
        setContent {
            AelitaFonTheme {
                ShellScreen(systemAgent = systemAgent)
            }
        }
    }
}
