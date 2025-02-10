package com.spendless.app

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

enum class TransactionType(@DrawableRes val iconRes: Int) {
    EXPENSE(R.drawable.trending_down),
    INCOME(R.drawable.trending_up);

    fun toDisplayString(): String {
        return when (this) {
            EXPENSE -> "Expense"
            INCOME -> "Income"
        }
    }
    @Composable
    fun toPainter(): Painter {
        return painterResource(id = iconRes)
    }
}
