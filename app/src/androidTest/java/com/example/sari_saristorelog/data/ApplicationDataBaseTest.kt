package com.example.sari_saristorelog.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.sari_saristorelog.Util.Transactions
import com.example.sari_saristorelog.data.repository.ApplicationDao
import com.example.sari_saristorelog.data.repository.ApplicationDataBase
import com.example.sari_saristorelog.data.transaction.Transaction
import com.example.sari_saristorelog.data.transaction.TransactionInfo
import com.example.sari_saristorelog.data.transaction.TransactionInfoAndCustomer
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ApplicationDataBaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: ApplicationDao
    private lateinit var db: ApplicationDataBase

    @Before
    fun setup(){
        db = Room
            .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ApplicationDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.dao()
    }

    @After
    fun closeDB(){
        db.close()
    }


    @Test
    fun insertTransactionInfo() = runTest(UnconfinedTestDispatcher()) {

        val noId = TransactionInfo(customerId = 1, createdDate = 1, total = 8.00)

        val id = dao.insertTransactionInfo(noId)

        val transaction1 = dao.getTransactionInfoList()
        val withId = TransactionInfo(transactionId = id, customerId = 1, createdDate = 1, total = 8.00)

        assertThat(transaction1).contains(withId)
    }

    @Test
    fun insertTransaction() = runTest {

        dao.insertTransactionInfo(Transactions.transactioninfo1)
        dao.insertItems(Transactions.transaction1.items)

        val transaction = dao.getTransaction(Transactions.transactioninfo1.transactionId)

        assertThat(transaction).isEqualTo(Transactions.transaction1)
    }

    @Test
    fun insertCustomer() = runTest {

        dao.insertCustomer(Transactions.customerInfo)

        val customer = dao.getCustomer(Transactions.customerInfo.customerId?: 0)

        assertThat(customer).isEqualTo(Transactions.customerInfo)
    }

    @Test
    fun deleteTransactionInfo() = runTest {

        dao.insertTransactionInfo(Transactions.transactioninfo1)
        dao.deleteTransactionInfo(Transactions.transactioninfo1)

        val transactionInfo = dao.getTransactionInfoList()

        assertThat(transactionInfo).isEmpty()
    }

    @Test
    fun deleteItems() = runTest {

        dao.insertTransactionInfo(Transactions.transactioninfo1)
        dao.insertItems(Transactions.transaction1.items)
        dao.deleteItems(Transactions.transaction1.items)

        val transaction = dao.getTransaction(Transactions.transactioninfo1.transactionId)

        assertThat(transaction).isEqualTo(Transaction(transactionInfo = Transactions.transactioninfo1,
        items = listOf()))
    }

    @Test
    fun deleteCustomer() = runTest {

        dao.insertCustomer(Transactions.customerInfo)
        dao.deleteCustomer(Transactions.customerInfo)

        val customer = dao.getCustomer(Transactions.customerInfo.customerId?: 0)

        assertThat(customer).isNull()
    }


    @Test
    fun getTransactionWithCustomerDesc() = runTest {
        dao.insertTransactionInfo(Transactions.transactioninfo1)
        dao.insertCustomer(Transactions.customerInfo)

        dao.insertCustomer(Transactions.customer2)
        dao.insertTransactionInfo(Transactions.transaction2)
        dao.insertTransactionInfo(Transactions.transaction3)

        val data = dao.getTransWithCustomerDesc()

        assertThat(data).isEqualTo(Transactions.transWithCustomer1)
    }

    @Test
    fun getTransactionWithCustomerAsc() = runTest {
        dao.insertTransactionInfo(Transactions.transactioninfo1)
        dao.insertCustomer(Transactions.customerInfo)

        dao.insertCustomer(Transactions.customer2)
        dao.insertTransactionInfo(Transactions.transaction2)
        dao.insertTransactionInfo(Transactions.transaction3)

        val data = dao.getTransWithCustomerAsc()

        assertThat(data).isEqualTo(Transactions.transWithCustomer1.reversed())
    }

    @Test
    fun getTransactionWithCustomerBetween2AboveDesc() = runTest {
        dao.insertTransactionInfo(Transactions.transactioninfo1)
        dao.insertCustomer(Transactions.customerInfo)

        dao.insertCustomer(Transactions.customer2)
        dao.insertTransactionInfo(Transactions.transaction2)
        dao.insertTransactionInfo(Transactions.transaction3)

        val data = dao.getTransWithCustomerBtwDateDesc(toDate = 2)

        assertThat(data).isEqualTo(Transactions.transWithCustomer1.filter { item ->
            item.createdDate <= 2 })
    }

    @Test
    fun getTransactionWithCustomerBetween2AboveAsc() = runTest {
        dao.insertTransactionInfo(Transactions.transactioninfo1)
        dao.insertCustomer(Transactions.customerInfo)

        dao.insertCustomer(Transactions.customer2)
        dao.insertTransactionInfo(Transactions.transaction2)
        dao.insertTransactionInfo(Transactions.transaction3)

        val data = dao.getTransWithCustomerBtwDateAsc(toDate = 2)

        assertThat(data).isEqualTo(Transactions.transWithCustomer1.filter { item ->
            item.createdDate <= 2 }.reversed())
    }

    @Test
    fun getTransactionWithCustomerBetween2AND3() = runTest {
        dao.insertTransactionInfo(Transactions.transactioninfo1)
        dao.insertCustomer(Transactions.customerInfo)

        dao.insertCustomer(Transactions.customer2)
        dao.insertTransactionInfo(Transactions.transaction2)
        dao.insertTransactionInfo(Transactions.transaction3)

        val data = dao.getTransWithCustomerBtwDateDesc(fromDate = 2, toDate = 3)

        assertThat(data).isEqualTo(Transactions.transWithCustomer1.filter { item ->
            (item.createdDate >= 2) && (item.createdDate <= 3)})
    }

    @Test
    fun getTransactionWithCustomerBetween2AND3Asc() = runTest {
        dao.insertTransactionInfo(Transactions.transactioninfo1)
        dao.insertCustomer(Transactions.customerInfo)

        dao.insertCustomer(Transactions.customer2)
        dao.insertTransactionInfo(Transactions.transaction2)
        dao.insertTransactionInfo(Transactions.transaction3)

        val data = dao.getTransWithCustomerBtwDateAsc(fromDate = 2, toDate = 3)

        assertThat(data).isEqualTo(Transactions.transWithCustomer1.filter { item ->
            (item.createdDate >= 2) && (item.createdDate <= 3)}.reversed())
    }

}
