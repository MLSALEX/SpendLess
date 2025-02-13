package com.spendless.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spendless.app.R
import com.spendless.app.expenseItems
import com.spendless.app.ui.components.AddButton
import com.spendless.app.ui.components.CategoryIcon
import com.spendless.app.ui.components.CategoryItem
import com.spendless.app.ui.components.CategoryName
import com.spendless.app.ui.components.CustomTextButton
import com.spendless.app.ui.components.ExpenseIncomeList
import com.spendless.app.ui.components.TopBar
import com.spendless.app.ui.theme.AppColors
import com.spendless.app.ui.theme.GradientScheme
import com.spendless.app.viewmodels.TransactionViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(
//    viewModel: DashboardViewModel = hiltViewModel(),
    viewModel: TransactionViewModel = hiltViewModel()
//    onSettingsClick: () -> Unit,
//    onExportClick: () -> Unit,
//    onShowAllClick: () -> Unit
) {
    val onSettingsClick = {  }
    val onExportClick = {  }
    val onShowAllClick = {  }

    val userName = "UserName" // by viewModel.userName.collectAsState()
    val accountBalance =  "$ 11, 382.45"  // by viewModel.accountBalance.collectAsState()
    val transactions = "transactions"  // by viewModel.transactions.collectAsState()
    val largestExpenseCategory = "largest expense" // by viewModel.largestExpenseCategory.collectAsState()
    val largestTransaction = "-$59.99"  // by viewModel.largestTransaction.collectAsState()
    val date = " Jan 7, 2005"
    val totalSpentLastWeek  = "-$852.20" // by viewModel.totalSpentLastWeek.collectAsState()
    val category = CategoryItem("Food & Groceries", R.drawable.food, AppColors.PrimaryFixed)
    val isIncome = false

    var showSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientScheme.DashboardGradient)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopBar(userName, onExportClick, onSettingsClick )
            },
            floatingActionButton = {
                AddButton(
                    onClick = { showSheet = true  },
                    icon = Icons.Default.Add,
                    contentDescription = "Add entry",
                    modifier = Modifier.padding(8.dp)
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            content = {innerPadding ->
                Column (
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ){
                    Column (Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    ){
                        AccountBalance(accountBalance)
                        AccountStatistics(category, isIncome, largestTransaction, date, totalSpentLastWeek)
                    }
                    LatestTransactions(
                        modifier = Modifier
                            .weight(1f),
                        onShowAllClick
                    )
                }
            }
        )
    }
    if (showSheet) {
        TransactionSheet(
            onDismiss = { showSheet = false },
            viewModel = viewModel
        )
    }
}

@Composable
fun LatestTransactions(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column (
        Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .padding(top = 16.dp, start = 12.dp, end = 12.dp)
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Latest Transactions",
                style = MaterialTheme.typography.titleLarge
            )
            CustomTextButton(text = "Show all", onClick = onClick)
        }
        ExpenseIncomeList(items = expenseItems)
    }
}

@Composable
fun AccountBalance(
    balance: String
) {
    Column(Modifier.fillMaxWidth()
        .fillMaxHeight(0.25f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = balance,  // ${uiState.accountBalance.formatCurrency()}",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Account Balance",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}


@Composable
fun AccountStatistics(
    category: CategoryItem,
    isIncome:Boolean,
    largestTransaction: String,
    date:String,
    totalSpentLastWeek: String
) {
    val size = 56.dp
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row (
            Modifier
                .fillMaxWidth()
                .background(
                    AppColors.OnPrimaryContainerOpacity12,
                    RoundedCornerShape(16.dp)
                )
                .padding(8.dp),
            Arrangement.spacedBy(10.dp),
            Alignment.CenterVertically
        ) {
            CategoryIcon( category, isIncome, size, iconSize = 30.dp)
            Column {
                CategoryName(
                    category.name,
                    MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary)
                Text(
                    text = "Most popular category",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            LargestTransaction(
                Modifier.weight(0.6f),
                largestTransaction,
                date
            )
            PreviousWeek(
                Modifier.weight(0.3f),
                totalSpentLastWeek
            )
        }
    }
}

@Composable
fun LargestTransaction(
    modifier: Modifier,
    largestTransaction: String,
    date:String
) {
    Row (
        modifier
            .background(
                AppColors.PrimaryFixed,
                RoundedCornerShape(16.dp)
            )
            .padding(14.dp),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Column {
            AdaptiveText(
                text = "Adobe Yearly",
                style = MaterialTheme.typography.titleLarge
            )
            AdaptiveText(
                text = "Largest transaction",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            AdaptiveText(
                text = largestTransaction,
                style = MaterialTheme.typography.titleLarge
            )
            AdaptiveText(
                text = date,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
@Composable
fun PreviousWeek(
    modifier: Modifier,
    totalSpentLastWeek: String
) {
    Row (
        modifier.fillMaxWidth(0.30f)
            .background(
                AppColors.SecondaryFixed,
                RoundedCornerShape(16.dp)
            )
            .padding(14.dp),

        verticalAlignment = Alignment.CenterVertically
    ){
        Column(horizontalAlignment = Alignment.Start) {
            AdaptiveText(
                text = totalSpentLastWeek,
                style = MaterialTheme.typography.titleLarge,
            )
            AdaptiveText(
                text = "Previous week",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun AdaptiveText(text: String, style: TextStyle = MaterialTheme.typography.bodyLarge) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    fun calculateSize(percentage: Float): TextUnit {
        return (screenWidth * (percentage / 100)).sp
    }

    val dynamicStyle = when (style) {
            MaterialTheme.typography.displayLarge -> style.copy(
                fontSize = calculateSize(10f),
                lineHeight = calculateSize(12f)
            )

            MaterialTheme.typography.titleLarge -> style.copy(
                fontSize = calculateSize(3.5f),
                lineHeight = calculateSize(5f)
            )

            MaterialTheme.typography.bodyLarge -> style.copy(
                fontSize = calculateSize(2.3f),
                lineHeight = calculateSize(4f)
            )

            else -> style
        }

    Text(
        text = text,
        style = dynamicStyle
    )
}