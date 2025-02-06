package com.spendless.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spendless.app.ui.components.AddButton
import com.spendless.app.ui.components.TopBar
import com.spendless.app.ui.theme.GradientScheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientScheme.DashboardGradient)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopBar()
            },
            floatingActionButton = {
                AddButton(
                    onClick = {  },
                    icon = Icons.Default.Add,
                    contentDescription = "Add entry",
                    modifier = Modifier.padding(8.dp)
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            content = {innerPadding ->

            }
        )
    }
}

