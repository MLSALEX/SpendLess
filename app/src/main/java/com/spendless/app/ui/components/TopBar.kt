package com.spendless.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spendless.app.R
import com.spendless.app.ui.theme.AppIconButtonColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text("UserName", color = MaterialTheme.colorScheme.onPrimary) },
        actions = {
            Row (modifier = modifier.padding(end = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)){
                IconButtonVariant(
                    contentDescription = "OnPrimary",
                    onClick = {  },
                    colors = AppIconButtonColors.OnPrimary,
                    iconResId = R.drawable.download
                )
                IconButtonVariant(
                    contentDescription = "OnPrimary",
                    onClick = {  },
                    colors = AppIconButtonColors.OnPrimary
                )
            }
        },
        colors = topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}
