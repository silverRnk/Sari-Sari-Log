package com.example.sari_saristorelog.feature_transaction_log.domain.use_cases

import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo

class FilterByDate {

    operator fun invoke(transactionInfoList: List<TransactionInfo>, fromDate: Long, toDate: Long): List<TransactionInfo>{
        return transactionInfoList.filter {
            it.createdDate in (fromDate..toDate)
        }
    }
}