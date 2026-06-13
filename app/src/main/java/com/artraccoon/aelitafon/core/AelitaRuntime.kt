package com.artraccoon.aelitafon.core

import android.content.Context
import com.artraccoon.aelitafon.apps.NormalAppLauncher
import com.artraccoon.aelitafon.apps.NormalAppRepository
import com.artraccoon.aelitafon.device.CapabilityRegistry
import com.artraccoon.aelitafon.device.NormalAppDeviceStateReader
import com.artraccoon.aelitafon.evolution.LocalSuggestionEngine
import com.artraccoon.aelitafon.logs.SharedPreferencesActionLogStore
import com.artraccoon.aelitafon.memory.SharedPreferencesMemoryRepository
import com.artraccoon.aelitafon.permissions.DefaultPermissionCenter
import com.artraccoon.aelitafon.policy.DefaultPolicyEngine
import com.artraccoon.aelitafon.projects.SharedPreferencesProjectRepository
import com.artraccoon.aelitafon.systemagent.AelitaSystemAgent
import com.artraccoon.aelitafon.systemagent.DefaultAelitaSystemAgent

data class AelitaRuntime(val systemAgent: AelitaSystemAgent) {
    companion object {
        fun create(applicationContext: Context): AelitaRuntime {
            val actionLogStore = SharedPreferencesActionLogStore(applicationContext)
            val localCore = DefaultAelitaLocalCore(
                memoryRepository = SharedPreferencesMemoryRepository(applicationContext),
                projectRepository = SharedPreferencesProjectRepository(applicationContext),
                actionLogStore = actionLogStore,
                suggestionEngine = LocalSuggestionEngine(),
            )
            return AelitaRuntime(
                systemAgent = DefaultAelitaSystemAgent(
                    deviceStateReader = NormalAppDeviceStateReader(applicationContext),
                    capabilityRegistry = CapabilityRegistry(),
                    policyEngine = DefaultPolicyEngine(),
                    localCore = localCore,
                    appRepository = NormalAppRepository(applicationContext),
                    appLauncher = NormalAppLauncher(applicationContext),
                    actionLogStore = actionLogStore,
                    permissionCenter = DefaultPermissionCenter(),
                ),
            )
        }
    }
}
