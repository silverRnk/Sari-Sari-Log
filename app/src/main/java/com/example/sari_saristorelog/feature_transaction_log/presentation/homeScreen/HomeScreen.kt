package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.component.TransactionItem

@Composable
fun HomeScreen(
    viewModel: HomeScreenVM = hiltViewModel()
){

    Column(modifier = Modifier.fillMaxWidth()){

        TextField(value = viewModel.searchText, onValueChange = {}, placeholder = { Text(text = "Search for Name")})

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()){
            items(viewModel.transactionList){ item ->
                TransactionItem(transactionInfo = item, onClick = { /*TODO*/ })
            }
        }

    }

}