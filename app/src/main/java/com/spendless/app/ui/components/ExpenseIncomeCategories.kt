package com.spendless.app.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spendless.app.R
import com.spendless.app.ui.theme.AppColors
import com.spendless.app.ui.theme.ExpenseIncomeColors

@Composable
fun ExpenseIncomeList(items: List<ExpenseIncomeItem>) {
    var expandedItem by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            ExpenseIncomeCategory(
                item = item.copy(isExpanded = item.title == expandedItem),
                onExpandClick = {
                    if (!item.description.isNullOrEmpty()) {
                        expandedItem = if (expandedItem == item.title) null else item.title
                    }
                }
            )
        }
    }
}

@Composable
fun ExpenseIncomeCategory(
    item: ExpenseIncomeItem,
    onExpandClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(ExpenseIncomeColors.itemBackgroundColor(item.isExpanded))
            .clickable { onExpandClick() }
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .padding(end = 2.dp, bottom = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CategoryIcon(item.category, item.isIncome, size = 44.dp)
                    if (!item.description.isNullOrEmpty()) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.BottomEnd)
                                .offset(x = 2.dp, y = 2.dp)
                        ){
                            NotesIcon(item.isExpanded, item.isIncome)
                        }
                    }
                }

                Column(Modifier.weight(1f)) {
                    Text(text = item.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,)
                    CategoryName(item.category.name)
                }
                Text(
                    text = item.amount,
                    color = ExpenseIncomeColors.amountColor(item.isIncome),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        if (item.isExpanded && !item.description.isNullOrEmpty()) {
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 52.dp)
            )
        }
    }
}


@Composable
fun NotesIcon(
    isExpanded: Boolean,
    isIncome: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(ExpenseIncomeColors.notesIconBackground)
            .size(20.dp),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(id = R.drawable.notes),
            contentDescription = "Notes",
            tint = ExpenseIncomeColors.notesIconTint(isExpanded, isIncome),
            modifier = modifier.size(14.dp)
        )
    }
}

data class ExpenseIncomeItem(
    val title: String,
    val category: CategoryItem,
    val amount: String,
    val isIncome: Boolean = false,
    val description: String?,
    var isExpanded: Boolean = false
)

data class CategoryItem (val name: String, @DrawableRes val icon: Int, val color: Color = AppColors.PrimaryFixed)
val categories = listOf(
    CategoryItem("Food & Groceries", R.drawable.food),
    CategoryItem("Home", R.drawable.home),
    CategoryItem("Entertainment", R.drawable.entertainment),
    CategoryItem("Education", R.drawable.education),
    CategoryItem("Clothing", R.drawable.clothing),
)