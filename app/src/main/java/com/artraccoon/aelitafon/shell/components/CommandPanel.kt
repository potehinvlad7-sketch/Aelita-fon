package com.artraccoon.aelitafon.shell.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artraccoon.aelitafon.ui.theme.AelitaViolet
import com.artraccoon.aelitafon.ui.theme.MutedViolet
import com.artraccoon.aelitafon.ui.theme.PanelBlack
import com.artraccoon.aelitafon.ui.theme.SoftViolet

@Composable
fun CommandPanel(
    commandText: String,
    localStatus: String,
    onCommandChange: (String) -> Unit,
    onSendCommand: () -> Unit,
    onVoiceClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(26.dp))
            .background(PanelBlack.copy(alpha = 0.92f))
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        OutlinedTextField(
            value = commandText,
            onValueChange = onCommandChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Скажи, что нужно сделать...",
                    color = MutedViolet,
                )
            },
            singleLine = false,
            minLines = 3,
            shape = RoundedCornerShape(22.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = SoftViolet,
                unfocusedTextColor = SoftViolet,
                cursorColor = AelitaViolet,
                focusedBorderColor = AelitaViolet,
                unfocusedBorderColor = MutedViolet.copy(alpha = 0.34f),
                focusedContainerColor = PanelBlack,
                unfocusedContainerColor = PanelBlack,
            ),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Button(
                onClick = onSendCommand,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AelitaViolet,
                    contentColor = PanelBlack,
                ),
                shape = RoundedCornerShape(18.dp),
            ) {
                Text(
                    text = "Отправить",
                    fontWeight = FontWeight.SemiBold,
                )
            }
            OutlinedButton(
                onClick = onVoiceClick,
                modifier = Modifier.weight(1f),
                border = BorderStroke(1.dp, AelitaViolet.copy(alpha = 0.52f)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = SoftViolet,
                ),
                shape = RoundedCornerShape(18.dp),
            ) {
                Text(text = "Голос")
            }
        }

        Text(
            text = localStatus,
            color = MutedViolet,
            fontSize = 13.sp,
            lineHeight = 18.sp,
        )
    }
}
