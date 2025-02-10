package com.spendless.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spendless.app.TransactionType
import com.spendless.app.ui.components.CategoryCard
import com.spendless.app.ui.components.SegmentedButton
import com.spendless.app.ui.components.SelectDropdown
import com.spendless.app.ui.components.categories
import com.spendless.app.viewmodels.TransactionViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionSheet(
    onDismiss: () -> Unit,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val selectedTypeIndex by viewModel.selectedTypeIndex.collectAsState()
    val currentTransactionType by viewModel.currentTransactionType.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val receiver by viewModel.receiver.collectAsState()
    val note by viewModel.note.collectAsState()
    val repeatFrequency by viewModel.repeatFrequency.collectAsState()
    val isCreateEnabled by viewModel.isCreateEnabled.collectAsState()
    val selectedCategory by viewModel.selectedExpenseCategory.collectAsState()


    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        keyboardController?.show()
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SegmentedButton(
                options = TransactionType.entries.map { it.toPainter() to it.toDisplayString() },
                selectedIndex = selectedTypeIndex,
                onOptionSelected = { viewModel.updateSelectedTypeIndex(it) }
            )

//            AmountInput(
//                amount = amount,
//                onAmountChange = { viewModel.updateAmount(it) },
//                showRedBrackets = preferencesRepository.useRedBrackets
//            )
//
//            // Receiver/Sender Input
//            OutlinedTextField(
//                value = receiver,
//                onValueChange = { viewModel.updateReceiver(it) },
//                label = { Text(if (currentTransactionType == TransactionType.EXPENSE) "Receiver" else "Sender") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            // Note Input
//            OutlinedTextField(
//                value = note,
//                onValueChange = { viewModel.updateNote(it) },
//                label = { Text("Add Note") },
//                modifier = Modifier.fillMaxWidth(),
//                textStyle = TextStyle(textAlign = TextAlign.Center)
//            )

//            if (currentTransactionType == TransactionType.EXPENSE) {
//                SelectDropdown(
//                    categories = categories,
//                    selectedCategory = selectedCategory ?: categories.first(),
//                    onCategorySelected = { viewModel.updateSelectedCategory(it) }
//                )
//            }

            if (currentTransactionType == TransactionType.EXPENSE) {
                SelectDropdown(
                    items = categories,
                    selectedItem = selectedCategory ?: categories.first(),
                    onItemSelected = { viewModel.updateSelectedCategory(it) }
                ) { category ->
                    CategoryCard(category, isIncome = false, iconSize = 40.dp)
                }
            }

            SelectDropdown(
                items = viewModel.repeatOptions,
                selectedItem = repeatFrequency,
                onItemSelected = { viewModel.updateRepeatFrequency(it) }
            ) { repeatOption ->
                Text(
                    text = repeatOption.toDisplayString(),
                    modifier = Modifier.padding(12.dp)
                )
            }
            // Create Button
            Button(
                onClick = {
                    scope.launch {
//                        viewModel.createTransaction()
                        onDismiss()
                    }
                },
                enabled = isCreateEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text("Create")
            }
        }
    }
}