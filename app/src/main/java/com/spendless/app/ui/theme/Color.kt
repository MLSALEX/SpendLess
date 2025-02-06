package com.spendless.app.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF5A00C8)
val OnPrimary = Color(0xFFFFFFFF)
val Secondary = Color(0xFF5F6200)
val TertiaryContainer = Color(0xFFC4E0F9)
val Error = Color(0xFFA40019)
val OnError = Color(0xFFFFFFFF)
val PrimaryContainer = Color(0xFF8138FF)
val SecondaryContainer = Color(0xFFD2E750)
val OnSecondaryContainer = Color(0xFF414300)
val OnPrimaryFixedVariant = Color(0xFF5900C7)
val InversePrimary = Color(0xFFD2BCFF)
val Surface = Color(0xFFFCF9F9)
val OnSurface = Color(0xFF1B1B1C)
val OnSurfaceVar = Color(0xFF44474B)
val Outline = Color(0xFF75777B)
val InverseSurface = Color(0xFF303031)
val InverseOnSurface = Color(0xFFF3F0F0)
val Background = Color(0xFFFEF7FF)
val OnBackground = Color(0xFF1D1A25)

object AppColors{
    val Success = Color(0xFF29AC08)
    val PrimaryFixed = Color(0xFFEADDFF)
    val SecondaryFixed = Color(0xFFE5EA58)
    val SecondaryFixedDim = Color(0xFFC9CE3E)
    val OnPrimaryFixed = Color(0xFF24005A)
    val SurfContainerLowest = Color(0xFFFFFFFF)
    val SurfContainerLow = Color(0xFFF6F3F3)
    val SurfContainer = Color(0xFFF0EDED)
    val SurfContainerHighest = Color(0xFFE4E2E2)
    val PrimaryOpacity12 = Color(0xFF5A00C8).copy(alpha = 0.12f)
    val PrimaryOpacity8 = Color(0xFF5A00C8).copy(alpha = 0.8f)
    val OnPrimaryOpacity12 = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val PrimaryContainerOpacity08 = Color(0xFF8138FF).copy(alpha = 0.08f)
    val OnPrimaryContainerOpacity12 = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val OnSecondaryContainerOpacity08 = Color(0xFF414300).copy(alpha = 0.08f)
    val OnSecondaryContainerOpacity12 = Color(0xFF414300).copy(alpha = 0.12f)
    val ErrorOpacity08 = Color(0xFFA40019).copy(alpha = 0.08f)
    val ErrorOpacity12 = Color(0xFFA40019).copy(alpha = 0.12f)
    val OnBackgroundOpacity08 = Color(0xFF1D1A25).copy(alpha = 0.08f)
    val OnBackgroundOpacity12 = Color(0xFF1D1A25).copy(alpha = 0.12f)
    val OnSurfaceOpacity12 = Color(0xFF1B1B1C).copy(alpha = 0.12f)
    val OnSurfaceVariantOpacity12 = Color(0xFF44474B).copy(alpha = 0.12f)
}

object GradientScheme {
    val DashboardGradient = Brush.radialGradient(
        colors = listOf(
            Color(0xFF5A00C8),
            Color(0xFF25004D)
        ),
        center = Offset(0f, 0f),
        radius = 500f
    )
}
object ButtonColors {
    @Composable
    fun filled(): ButtonColors {
        return ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    @Composable
    fun textBtn(pressed: Boolean = false): ButtonColors {
        return ButtonDefaults.buttonColors(
            containerColor = if (pressed) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f) else Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

