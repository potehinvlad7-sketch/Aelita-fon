package com.artraccoon.aelitafon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artraccoon.aelitafon.ui.theme.AelitaFonTheme
import com.artraccoon.aelitafon.ui.theme.AelitaViolet
import com.artraccoon.aelitafon.ui.theme.DeepBlack
import com.artraccoon.aelitafon.ui.theme.PanelBlack
import com.artraccoon.aelitafon.ui.theme.SoftViolet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AelitaFonTheme {
                AelitaShellScreen()
            }
        }
    }
}

@Composable
fun AelitaShellScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = DeepBlack,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepBlack)
                .padding(28.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .background(PanelBlack)
                    .padding(horizontal = 28.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Аэлита",
                    color = SoftViolet,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "система бодрствует",
                    color = AelitaViolet,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "Aelita-Fon v0.1",
                    color = SoftViolet.copy(alpha = 0.78f),
                    fontSize = 15.sp,
                )
                Text(
                    text = "локальная ИИ-среда телефона",
                    color = SoftViolet.copy(alpha = 0.58f),
                    fontSize = 13.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AelitaShellScreenPreview() {
    AelitaFonTheme {
        AelitaShellScreen()
    }
}
