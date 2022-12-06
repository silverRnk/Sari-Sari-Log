package com.example.sari_saristorelog.feature_transaction_log.domain.use_cases

import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo

class FilterByName {

    operator fun invoke(transactionInfoList: List<TransactionInfo>, name: String): List<TransactionInfo>{
        return transactionInfoList.filter {
            it.customerName.lowercase() == name.lowercase()
        }
    }

}