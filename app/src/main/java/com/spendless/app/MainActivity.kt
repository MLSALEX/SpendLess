package com.spendless.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spendless.app.ui.components.CategoryCard
import com.spendless.app.ui.components.CategoryItem
import com.spendless.app.ui.components.ExpenseIncomeItem
import com.spendless.app.ui.components.IconButtonDemo
import com.spendless.app.ui.components.SegmentedButton
import com.spendless.app.ui.components.categories
import com.spendless.app.ui.screens.Dashboard
import com.spendless.app.ui.theme.AppColors
import com.spendless.app.ui.theme.SpendLessTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendLessTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Dashboard()
//                    TestScreen()
                }
            }
        }
    }
}

@Composable
fun TestScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colorScheme.background)
    ){
        val sampleCategory = CategoryItem("Food & Groceries", R.drawable.food, AppColors.PrimaryFixed)
        CategoryCard(sampleCategory, isIncome = true, 40.dp)
        Spacer(modifier = Modifier.height(15.dp))
        var selectedIndex by remember { mutableStateOf(0) }

        SegmentedButton(
            options = listOf("Label", "Label", "Label"),
            selectedIndex = selectedIndex,
            onOptionSelected = { selectedIndex = it }
        )
//          ExpenseIncomeList(items = expenseItems)
        Spacer(modifier = Modifier.height(15.dp))
//        SelectList()

//        var selectedCategory by remember { mutableStateOf(categories.first()) }
//
//        Column(modifier = Modifier.padding(16.dp)) {
//            SelectDropdown(
//                categories = categories,
//                selectedCategory = selectedCategory,
//                onCategorySelected = { selectedCategory = it } // Обновляем выбранную категорию
//            )
//        }
        IconButtonDemo()

    }
}

val expenseItems = listOf(
    ExpenseIncomeItem(
        title = "Starbucks",
        category = categories[0], // Food & Groceries
        amount = "-$7.50",
        description = null
    ),
    ExpenseIncomeItem(
        title = "Home Rent",
        category = categories[1], // Home
        amount = "-$1200.00",
        description = "Monthly rent payment."
    ),
    ExpenseIncomeItem(
        title = "Cinema",
        category = categories[2], // Entertainment
        amount = "-$15.00",
        description = null
    ),
    ExpenseIncomeItem(
        title = "Rick’s share - Birthday M.",
        category = categories[0], // Food & Groceries
        amount = "$20.00",
        isIncome = true,
        description = "Received money for a birthday gift."
    ),
    ExpenseIncomeItem(
        title = "Freelance Work",
        category = categories[1], // Home
        amount = "$500.00",
        isIncome = true,
        description = "Payment for completed project."
    )
)


//(care, clothing, education, entertainment, food, health, other, saving, transport)