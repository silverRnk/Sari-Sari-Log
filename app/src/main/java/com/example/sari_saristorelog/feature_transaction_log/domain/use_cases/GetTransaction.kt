package com.example.sari_saristorelog.feature_transaction_log.domain.use_cases

import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.repository.LoggerRepository
import javax.inject.Inject

class GetTransaction @Inject constructor(
    private val repository: LoggerRepository
) {

    suspend operator fun invoke(
        id: Long
    ): Transaction?{
        return repository.getTransaction(id)
    }

}