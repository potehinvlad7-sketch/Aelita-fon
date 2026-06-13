package com.artraccoon.aelitafon.shell.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artraccoon.aelitafon.shell.SystemNode
import com.artraccoon.aelitafon.ui.theme.AelitaViolet
import com.artraccoon.aelitafon.ui.theme.CardViolet
import com.artraccoon.aelitafon.ui.theme.MutedViolet
import com.artraccoon.aelitafon.ui.theme.SoftViolet

@Composable
fun SystemNodeCard(
    node: SystemNode,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 104.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardViolet),
        border = BorderStroke(1.dp, AelitaViolet.copy(alpha = 0.18f)),
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = node.title,
                color = SoftViolet,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = node.subtitle,
                color = MutedViolet,
                fontSize = 13.sp,
                lineHeight = 17.sp,
            )
        }
    }
}
