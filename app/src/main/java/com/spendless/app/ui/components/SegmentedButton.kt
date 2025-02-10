package com.spendless.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendless.app.ui.theme.AppColors

@Composable
fun SegmentedButton(
    options: List<Pair<Painter?, String>>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(AppColors.PrimaryContainerOpacity08)
            .padding(4.dp)
    ) {
        options.forEachIndexed { index, (icon, option) ->
            Button(
                onClick = { onOptionSelected(index) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (index == selectedIndex) AppColors.SurfContainerLowest else Color.Transparent,
                    contentColor = if (index == selectedIndex) MaterialTheme.colorScheme.primary else AppColors.OnPrimaryFixed
                ),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    icon?.let {
                        Icon(
                            painter = it,
                            contentDescription = option,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text(text = option)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSegmentedButton() {
    var selectedIndex by remember { mutableStateOf(0) }

    SegmentedButton(
        options = listOf(
            null to "Label",
            null to "Label"
        ),
        selectedIndex = selectedIndex,
        onOptionSelected = { selectedIndex = it }
    )
}
