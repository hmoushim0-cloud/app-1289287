package sa.mostaql.accountingfollowup.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.mostaql.accountingfollowup.data.FinancialAccountRepository
import sa.mostaql.accountingfollowup.model.FinancialAccount
import sa.mostaql.accountingfollowup.model.AccountActivity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FinancialAccountViewModel(
    private val repository: FinancialAccountRepository
) : ViewModel() {
    val catalog: StateFlow<List<AccountActivity>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<FinancialAccount?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: FinancialAccount) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
