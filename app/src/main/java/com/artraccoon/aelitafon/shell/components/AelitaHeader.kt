package com.artraccoon.aelitafon.shell.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artraccoon.aelitafon.ui.theme.AelitaViolet
import com.artraccoon.aelitafon.ui.theme.PanelBlack
import com.artraccoon.aelitafon.ui.theme.SoftViolet

@Composable
fun AelitaHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(PanelBlack)
            .padding(horizontal = 24.dp, vertical = 26.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = "Аэлита",
            color = SoftViolet,
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = "система бодрствует",
            color = AelitaViolet,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = "локальная ИИ-среда телефона",
            color = SoftViolet.copy(alpha = 0.66f),
            fontSize = 14.sp,
        )
    }
}
