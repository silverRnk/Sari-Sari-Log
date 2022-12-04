package com.example.sari_saristorelog.feature_transaction_log.presentation.HomeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sari_saristorelog.feature_transaction_log.presentation.HomeScreen.component.TransactionItem


@Composable
fun HomeScreen(
    viewModel: HomeScreenVM = hiltViewModel()
){

    Column(modifier = Modifier.fillMaxSize(), 
        horizontalAlignment = Alignment.CenterHorizontally) {
        
        Spacer(modifier = Modifier.height(5.dp))
        
        TextField(value = viewModel.searchText, 
            onValueChange = {viewModel.onEvent(HomeScreenEvent.OnSearchValueChange(it))},
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(30.dp),
        placeholder = {Text(text = "Search for Name")})

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(){
            items(viewModel.transactionList){ item ->
                TransactionItem(transactionItem = item, OnClick = {})
            }

        }
        
    }
    
}