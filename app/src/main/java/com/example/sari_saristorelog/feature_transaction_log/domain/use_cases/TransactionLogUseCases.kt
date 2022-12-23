package com.example.sari_saristorelog.feature_transaction_log.domain.use_cases

import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo

data class TransactionLogUseCases(
    val getTransactionInfoList: GetTransactionInfoList,
    val filterByDate: FilterByDate,
    val filterByName: FilterByName,
    val addTransaction: AddTransaction
)