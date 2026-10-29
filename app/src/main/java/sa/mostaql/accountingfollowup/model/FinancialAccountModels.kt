package sa.mostaql.accountingfollowup.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountActivity(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val icon: String = "⭐"
)

@Serializable
data class FinancialAccount(
    val requestId: String,
    val items: List<AccountActivity>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: FinancialAccountStatus = FinancialAccountStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class FinancialAccountStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
