package com.spendless.app.viewmodels

import android.icu.util.ULocale
import android.view.SurfaceControl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spendless.app.RepeatFrequency
import com.spendless.app.TransactionType
import com.spendless.app.ui.components.CategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
//    private val encryptionService: EncryptionService,
//    private val transactionRepository: TransactionRepository,
//    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _selectedTypeIndex = MutableStateFlow(0)
    val selectedTypeIndex = _selectedTypeIndex.asStateFlow()

    private val _amount = MutableStateFlow("00.00")
    val amount = _amount.asStateFlow()

    private val _receiver = MutableStateFlow("")
    val receiver = _receiver.asStateFlow()

    private val _note = MutableStateFlow("")
    val note = _note.asStateFlow()


    private val _selectedCategory = MutableStateFlow<CategoryItem?>(null)
    val selectedExpenseCategory = _selectedCategory.asStateFlow()

    private val _repeatFrequency = MutableStateFlow(RepeatFrequency.DOES_NOT_REPEAT)
    val repeatFrequency: StateFlow<RepeatFrequency> = _repeatFrequency.asStateFlow()

    val repeatOptions: List<RepeatFrequency> = RepeatFrequency.entries

    fun updateRepeatFrequency(newFrequency: RepeatFrequency) {
        _repeatFrequency.value = newFrequency
    }

    val currentTransactionType = selectedTypeIndex.map {
        TransactionType.values()[it]
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TransactionType.EXPENSE)

    val isCreateEnabled = combine(amount, receiver) { amount, receiver ->
        amount != "00.00" && receiver.trim().let {
            it.length in 3..14 && it.all { char -> char.isLetterOrDigit() }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    fun updateSelectedTypeIndex(index: Int) {
        _selectedTypeIndex.value = index
    }

    fun updateAmount(newAmount: String) {
        _amount.value = newAmount
    }

    fun updateReceiver(newReceiver: String) {
        _receiver.value = newReceiver
    }

    fun updateNote(newNote: String) {
        _note.value = newNote.take(100)
    }


    fun updateSelectedCategory(category: CategoryItem) {
        _selectedCategory.value = category
    }

//    fun createTransaction() {
//        viewModelScope.launch {
//            val transaction = SurfaceControl.Transaction(
//                type = currentTransactionType.value,
//                amount = amount.value.toBigDecimal(),
//                receiver = receiver.value.trim(),
//                note = note.value,
//                repeatFrequency = repeatFrequency.value,
//                expenseCategory = selectedExpenseCategory.value
//            )
//
//            val encryptedTransaction = encryptionService.encrypt(transaction)
//            transactionRepository.saveTransaction(encryptedTransaction)
//        }
//    }
}