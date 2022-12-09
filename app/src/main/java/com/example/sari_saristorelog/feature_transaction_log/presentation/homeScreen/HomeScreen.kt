package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.component.TransactionItem
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@Composable
fun HomeScreen(
    viewModel: HomeScreenVM = hiltViewModel()
){
    val listState = viewModel.transactionInfoList.value
    val textFieldState = viewModel.searchBoxState.value
    val dateFilterState = viewModel.onDateFilterState.value


    //@Todo move the local date to viewmodel
    val fromLocalDate = viewModel
        .convertLongDateToLocalDate(dateFilterState.fromDate)
    val toLocalDate = viewModel
        .convertLongDateToLocalDate(dateFilterState.toDate)



    val fromDatePickerDialogState = rememberMaterialDialogState()

    val toDatePickerDialog = rememberMaterialDialogState()

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.fillMaxWidth()){
            TextField(value = textFieldState.text,
                onValueChange = {viewModel.onEvent(HomeScreenEvent.OnSearchValueChange(it))},
                placeholder = { Text(text = "Search for Name")},
            modifier = Modifier.align(Alignment.Center).fillMaxWidth(0.7f))

            IconButton(onClick = {viewModel.onEvent(HomeScreenEvent.OnFilterVisibilityToggle)}, modifier = Modifier.align(Alignment.CenterEnd)) {

                Icon(painter = painterResource(id = com.example.sari_saristorelog.R.drawable.ic_filter_list),
                    contentDescription = "Filter",
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .padding(end = 10.dp))

            }
        }



        AnimatedVisibility(
            visible = dateFilterState.isEnable,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp).background(Color.White),
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(modifier = Modifier.wrapContentSize()) {
                    Text("From:")
                    Row(
                        modifier = Modifier.wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = fromLocalDate.toString(),
                            modifier = Modifier
                                .background(Color.White)
                                .width(100.dp),
                            style = MaterialTheme.typography.h3
                        )

                        Spacer(modifier = Modifier.fillMaxWidth(0.1f))

                        IconButton(onClick = { fromDatePickerDialogState.show() }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "from_date"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column(modifier = Modifier.wrapContentSize()) {
                    Text("To:")
                    Row(
                        modifier = Modifier.wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = toLocalDate.toString(),
                            modifier = Modifier
                                .background(Color.White)
                                .width(100.dp),
                            style = MaterialTheme.typography.h3
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        IconButton(onClick = { toDatePickerDialog.show() }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "from_date"
                            )
                        }
                    }
                }

            }
        }



        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally){
            items(listState){ item ->
                TransactionItem(transactionInfo = item, onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(70.dp)
                        .background(if (!item.isConfirmed) Color.White else Color.LightGray))
            }
        }

    }

    var selectedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    MaterialDialog(
        dialogState = fromDatePickerDialogState,
        buttons = {
            positiveButton(
                text = "Ok",
                onClick = {
                    viewModel.onEvent(HomeScreenEvent.OnFromFilterDateSelected(selectedDate))
                }
            )
            negativeButton(
                text = "Cancel"
            )
        }
    ) {

        datepicker(
            initialDate = fromLocalDate,
            title = "Pick a Date",
            waitForPositiveButton = true
        ){
            selectedDate = it
        }

    }

    MaterialDialog(
        dialogState = toDatePickerDialog,
        buttons = {
            positiveButton(
                text = "Ok",
                onClick = {
                    viewModel.onEvent(HomeScreenEvent.OnToFilterDateSelected(selectedDate))
                }
            )
            negativeButton(
                text = "Cancel"
            )
        }
    ) {

        datepicker(
            initialDate = toLocalDate,
            title = "Pick a Date",
            waitForPositiveButton = true
        ){
            selectedDate = it
        }
    }


}
