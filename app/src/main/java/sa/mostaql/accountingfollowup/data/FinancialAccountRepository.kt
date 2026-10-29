package sa.mostaql.accountingfollowup.data

import sa.mostaql.accountingfollowup.model.FinancialAccount
import sa.mostaql.accountingfollowup.model.AccountActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface FinancialAccountRepository {
    fun getAvailableCatalog(): Flow<List<AccountActivity>>
    fun getActiveRequest(): Flow<FinancialAccount?>
    suspend fun submitRequest(request: FinancialAccount): Result<String>
}

class InMemoryFinancialAccountRepository : FinancialAccountRepository {
    private val _catalog = MutableStateFlow<List<AccountActivity>>(listOf(
        AccountActivity("1", "باقة الخدمة الأساسية", "تنفيذ متقن ومطابق للمواصفات", 4500L, "⭐"),
        AccountActivity("2", "باقة الخدمة المتقدمة", "دعم فني وضمان جودة الأداء", 9500L, "⚡"),
        AccountActivity("3", "باقة VIP المتكاملة", "تغطية شاملة ومتابعة لحظية", 14000L, "💎")
    ))
    private val _active = MutableStateFlow<FinancialAccount?>(null)

    override fun getAvailableCatalog(): Flow<List<AccountActivity>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<FinancialAccount?> = _active.asStateFlow()

    override suspend fun submitRequest(request: FinancialAccount): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
