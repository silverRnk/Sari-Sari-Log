package com.example.sari_saristorelog.util

object QueryKeys {

/*    const val transInfoWithCustomerDesc = "SELECT TransactionInfo.transactionId as transactionId, Customer.nickName as nickName" +
    ", Customer.avatar as avatar, TransactionInfo.confirmedDate as confirmedDate" +
    ", TransactionInfo.editedDate as editedDate, TransactionInfo.createdDate as createdDate" +
    ", TransactionInfo.isConfirmed as isConfirmed, TransactionInfo.total as total" +
    " FROM TransactionInfo, Customer WHERE TransactionInfo.customerId = Customer.customerId" +
            "ORDER BY createdDate DESC"*/

    const val transInfoWithCustomerDesc1 = "SELECT TransactionInfo.transactionId as transactionId, Customer.nickName as nickName" +
            ", Customer.avatar as avatar, TransactionInfo.confirmedDate as confirmedDate" +
            ", TransactionInfo.editedDate as editedDate, TransactionInfo.createdDate as createdDate" +
            ", TransactionInfo.isConfirmed as isConfirmed, TransactionInfo.total as total" +
            " FROM Customer INNER JOIN TransactionInfo ON TransactionInfo.customerId = Customer.customerId"



/*    const val transInfoWithCustomerAsc = "SELECT TransactionInfo.transactionId as transactionId, Customer.nickName as nickName" +
            ", Customer.avatar as avatar, TransactionInfo.confirmedDate as confirmedDate" +
            ", TransactionInfo.editedDate as editedDate, TransactionInfo.createdDate as createdDate" +
            ", TransactionInfo.isConfirmed as isConfirmed, TransactionInfo.total as total" +
            " FROM TransactionInfo, Customer WHERE TransactionInfo.customerId = Customer.customerId" +
            " ORDER BY createdDate Asc"*/

}


