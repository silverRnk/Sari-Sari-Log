package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.component.TransactionItem

@Composable
fun HomeScreen(
    viewModel: HomeScreenVM = hiltViewModel()
){

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.fillMaxWidth()){
            TextField(value = viewModel.searchText,
                onValueChange = {},
                placeholder = { Text(text = "Search for Name")},
            modifier = Modifier.align(Alignment.Center))

            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterEnd)) {

                Icon(painter = painterResource(id = com.example.sari_saristorelog.R.drawable.ic_filter_list),
                    contentDescription = "Filter",
                modifier = Modifier.height(40.dp).width(40.dp).padding(end = 10.dp))

            }
        }
        


        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally){
            items(viewModel.transactionList){ item ->
                TransactionItem(transactionInfo = item, onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(70.dp)
                        .background(if (!item.isConfirmed) Color.White else Color.LightGray))
            }
        }

    }

}