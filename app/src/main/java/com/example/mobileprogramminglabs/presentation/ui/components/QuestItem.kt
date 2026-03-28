package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.Thistle
import com.example.mobileprogramminglabs.presentation.ui.util.QuestData

@Composable
fun QuestItem(
    quest: QuestData,
    onDeleteClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.size_small)))
            .background(DeepTeal)
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Checkbox(
                checked = quest.isCompleted,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Thistle,
                    uncheckedColor = Thistle,
                    checkmarkColor = Color.White
                )
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.width_xmedium)))
            Column {
                Text(
                    text = quest.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = AliceBlue
                )
                Text(
                    text = "+${quest.xp} XP",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AliceBlue
                )
            }
        }
        IconButton(onClick = onDeleteClick) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_quest),
                tint = AliceBlue
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestItemPreview() {
    QuestItem(
        quest = QuestData(
            id = 1,
            title = "Study Kotlin",
            xp = 20,
            isCompleted = false
        ),
        onDeleteClick = {},
        onCheckedChange = {}
    )
}