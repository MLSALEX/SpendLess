package com.spendless.app.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spendless.app.R
import com.spendless.app.ui.theme.AppIconButtonColors
import com.spendless.app.ui.theme.IconButtonColorsScheme

@Composable
fun IconButtonTemplate(
    contentDescription: String,
    onClick: () -> Unit,
    colors: IconButtonColorsScheme,
    isEnabled: Boolean = true,
    @DrawableRes iconResId: Int
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor by animateColorAsState(
        targetValue = colors.containerColor(isPressed),
        label = "buttonBackgroundColor"
    )

    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = true),
                enabled = isEnabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = if (isEnabled) colors.contentColor() else colors.disabledContentColor()
        )
    }
}

@Composable
fun IconButtonVariant(
    contentDescription: String,
    onClick: () -> Unit,
    colors: IconButtonColorsScheme,
    isEnabled: Boolean = true,
    @DrawableRes iconResId: Int = R.drawable.settings
) {
    IconButtonTemplate(
        contentDescription = contentDescription,
        onClick = onClick,
        colors = colors,
        isEnabled = isEnabled,
        iconResId = iconResId
    )
}

@Composable
fun IconButtonDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButtonVariant(
            contentDescription = "Filled",
            onClick = {  },
            colors = AppIconButtonColors.Filled
        )

        IconButtonVariant(
            contentDescription = "Standard",
            onClick = {  },
            colors = AppIconButtonColors.Standard
        )

        IconButtonVariant(
            contentDescription = "Error",
            onClick = {  },
            colors = AppIconButtonColors.Error
        )
    }
}
