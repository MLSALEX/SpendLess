package com.spendless.app.ui.components

import android.icu.util.ULocale
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.spendless.app.R
import com.spendless.app.ui.theme.AppColors
import com.spendless.app.ui.theme.ExpenseIncomeColors

@Composable
fun CategoryCard(
    category: CategoryItem,
    isIncome: Boolean,
    iconSize: Dp = 44.dp
) {
    Row(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryIcon(category, isIncome, iconSize)
        Column(Modifier.weight(1f)) {
            CategoryName(category.name, MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun CategoryIcon(
    item:CategoryItem,
    isIncome: Boolean,
    size: Dp = 44.dp
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(ExpenseIncomeColors.categoryIconBackground(isIncome))
            .size(size),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(item.icon),
            contentDescription = item.name,
            modifier = Modifier.size(20.dp),
            tint = Color.Unspecified
        )
    }
}

@Composable
fun CategoryName(
    name: String,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge
) {
    Text(
        text = name,
        style = textStyle,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryCard() {
    val sampleCategory = CategoryItem("Food & Groceries", R.drawable.food, AppColors.PrimaryFixed)

    MaterialTheme {
        CategoryCard(
            category = sampleCategory,
            isIncome = false,
        )
    }
}