package com.example.sari_saristorelog.feature_transaction_log.domain.use_cases

import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.feature_transaction_log.domain.repository.LoggerRepository
import com.example.sari_saristorelog.feature_transaction_log.domain.util.FilterBy
import com.example.sari_saristorelog.feature_transaction_log.domain.util.QueryOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime

class GetTransactionInfoList(
    private val repository: LoggerRepository
) {

    fun get(filterBy: FilterBy): Flow<List<TransactionInfo>>{
        return when(filterBy.order){
            is QueryOrder.Desc -> {
                when(filterBy){
                    is FilterBy.NoFilter -> {
                        repository.getTransInfo().map { it.sortedByDescending { item -> item.createdDate } }
                    }
                    is FilterBy.Date -> {
                        repository.getTransInfo().map {
                            it.filter { item ->
                                item.createdDate!! >= filterBy.fromDate && item.createdDate <= filterBy.toDate
                            }.sortedByDescending { item -> item.createdDate } }
                    }
                    is FilterBy.Name -> {
                        repository.getTransInfo().map {
                            it.filter { item ->
                                item.customerName.lowercase() == filterBy.name.lowercase()
                            }.sortedByDescending { item -> item.createdDate }
                        }
                    }
                    is FilterBy.DateAndName -> {
                        repository.getTransInfo().map {
                            it.filter { item ->
                                item.createdDate!! >= filterBy.fromDate &&
                                        item.createdDate!! <= filterBy.toDate &&
                                        item.customerName.lowercase() == filterBy.name.lowercase()
                            }.sortedByDescending { item -> item.createdDate }
                        }
                    }
                }
            }
            is QueryOrder.Asc -> {
                when(filterBy){
                    is FilterBy.NoFilter -> {
                        repository.getTransInfo().map { it.sortedByDescending { item -> item.createdDate } }
                    }
                    is FilterBy.Date -> {
                        repository.getTransInfo().map {
                            it.filter { item ->
                                item.createdDate!! >= filterBy.fromDate && item.createdDate!! <= filterBy.toDate
                            }.sortedBy { item -> item.createdDate } }
                    }
                    is FilterBy.Name -> {
                        repository.getTransInfo().map {
                            it.filter { item ->
                                item.customerName.lowercase() == filterBy.name.lowercase()
                            }.sortedBy { item -> item.createdDate }
                        }
                    }
                    is FilterBy.DateAndName -> {
                        repository.getTransInfo().map {
                            it.filter { item ->
                                item.createdDate!! >= filterBy.fromDate &&
                                        item.createdDate <= filterBy.toDate &&
                                        item.customerName.lowercase() == filterBy.name.lowercase()
                            }.sortedBy { item -> item.createdDate }
                        }
                    }
                }

            }

        }
    }
}