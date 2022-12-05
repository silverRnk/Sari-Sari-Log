package com.example.sari_saristorelog.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.sari_saristorelog.Util.Transactions
import com.example.sari_saristorelog.core.data.repository.ApplicationDao
import com.example.sari_saristorelog.core.data.repository.ApplicationDataBase
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
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

        val noId = TransactionInfo(customerName = "Pat", customerIcon = 1, createdDate = 1, total = 8.00)

        val id = dao.insertTransactionInfo(noId)

        val transaction1 = dao.getTransactionInfoList()
        val withId = TransactionInfo(transactionId = id, customerName = "Pat", customerIcon = 1, createdDate = 1, total = 8.00)

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

        assertThat(transaction).isEqualTo(
            Transaction(transactionInfo = Transactions.transactioninfo1,
        items = listOf())
        )
    }

    @Test
    fun getTransactionInfo() = runTest {
        dao.insertTransactionInfo(Transactions.transactioninfo1)
        dao.insertTransactionInfo(Transactions.transaction2)
        dao.insertTransactionInfo(Transactions.transaction3)

        val transactionList = dao.getTransactionInfoList()

        assertThat(transactionList).isNotEmpty()

    }

}
