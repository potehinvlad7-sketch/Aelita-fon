package com.artraccoon.aelitafon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.artraccoon.aelitafon.core.AelitaRuntime
import com.artraccoon.aelitafon.shell.ShellScreen
import com.artraccoon.aelitafon.ui.theme.AelitaFonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val runtime = AelitaRuntime.create(applicationContext)
        setContent {
            AelitaFonTheme {
                ShellScreen(systemAgent = runtime.systemAgent)
            }
        }
    }
}
