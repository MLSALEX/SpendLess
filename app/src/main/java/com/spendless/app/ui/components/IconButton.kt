package com.spendless.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spendless.app.ui.theme.AppColors
import com.spendless.app.ui.theme.AppIconButtonColors

@Composable
fun IconButton(
    iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isEnabled) AppColors.OnPrimaryContainerOpacity12
                else AppColors.PrimaryContainerOpacity08,
            )
    ) {
        androidx.compose.material3.IconButton(
            onClick = onClick,
            enabled = isEnabled,
            colors = AppIconButtonColors.transparent(),
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
