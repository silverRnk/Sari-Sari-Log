package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.AddItem
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.AddItemDialog
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.CustomerInfoFill
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.Item
import com.example.sari_saristorelog.feature_transaction_log.presentation.util.PickerColor
import com.example.sari_saristorelog.ui.theme.Surface1
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.customView
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.format.DateTimeFormatter

@Composable
fun AddEditTransactionScreen(
    navController: NavController,
    viewModel: AddEditTransactionViewModel = hiltViewModel()
){

    val itemsState = viewModel.itemState
    val customerInfoState = viewModel.customerInfoState
    val dateState = viewModel.dateState

    val dateFormatter by remember {
        mutableStateOf(
            derivedStateOf {
                DateTimeFormatter.ofPattern("MMM, dd yyyy")
                    .format(dateState.value.currentDate)
            }
        )
    }

    val timeFormatter by remember {
        mutableStateOf(
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("hh:mm")
                    .format(dateState.value.currentTime)
            }
        )
    }

    val datePickerDialog = rememberMaterialDialogState()
    val timePickerDialog = rememberMaterialDialogState()
    val addItemDialog = rememberMaterialDialogState()

    var pickedDate by remember {
        mutableStateOf(dateState.value.currentDate)
    }

    var pickedTime by remember {
        mutableStateOf(dateState.value.currentTime)
    }

    LaunchedEffect(key1 = 1){
        //TODO add navigation
    }


    MaterialDialog(
        dialogState = datePickerDialog,
        backgroundColor = Color.White,
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = MaterialTheme.typography.h3.copy(fontSize = 15.sp, color = Color.Black),
                onClick = {
                    viewModel.onEvent(AddEditTransactionEvent.OnChangeDate(pickedDate))
                }
            )

            negativeButton(
                text = "Cancel",
                textStyle = MaterialTheme.typography.h3.copy(fontSize = 15.sp,color = Color.Black))
        }
    ) {


        datepicker(
            initialDate = pickedDate,
            title = "Pick a Date",
            colors = PickerColor.theme1,
            waitForPositiveButton = true,
            onDateChange = {pickedDate = it}
        )

    }

    MaterialDialog(
        dialogState = timePickerDialog,
        backgroundColor = Color.White,
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = MaterialTheme.typography.h3.copy(fontSize = 15.sp, color = Color.Black),
                onClick = {
                    viewModel.onEvent(AddEditTransactionEvent.OnChangeTime(pickedTime))
                }
            )

            negativeButton(
                text = "Cancel",
                textStyle = MaterialTheme.typography.h3.copy(fontSize = 15.sp,color = Color.Black))
        }
    ) {


        timepicker(
            initialTime = pickedTime,
            title = "Pick a Time",
            waitForPositiveButton = true,
            onTimeChange = {pickedTime = it}
        )
    }

    var description by remember {
        mutableStateOf("")
    }

    var quantity by remember {
        mutableStateOf("")
    }

    var price by remember {
        mutableStateOf("")
    }

    var subtotal by remember {
        mutableStateOf("")
    }

    MaterialDialog(
        dialogState = addItemDialog,
        backgroundColor = Surface1,
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = MaterialTheme.typography.h3.copy(fontSize = 15.sp, color = Color.Black),
                onClick = {
                }
            )

            negativeButton(
                text = "Cancel",
                textStyle = MaterialTheme.typography.h3.copy(fontSize = 15.sp,color = Color.Black))
        }
    ) {

        customView {
            AddItemDialog(
                description = description,
                quantity = quantity,
                price = price,
                subtotal = subtotal,
                onDescriptionChange = {},
                onQuantityChange = {},
                onPriceChange = {},
                onSubtotalChange = {}
            )
        }
    }


    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(10.dp))

        CustomerInfoFill(
            customerIcon = customerInfoState.value.customerIcon,
            customerName = customerInfoState.value.name,
            onNameChange = {viewModel.onEvent(AddEditTransactionEvent.OnNameTextFieldChange(it))},
            onToggleIconSelection = {viewModel.onEvent(AddEditTransactionEvent.OnToggleIconSelection)},
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight())

        Spacer(modifier = Modifier.height(20.dp))

        //Total
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()){

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                Text(
                    text = dateFormatter.value,
                    style = MaterialTheme.typography.h3,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            datePickerDialog.show()
                        })

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = timeFormatter.value,
                    style = MaterialTheme.typography.h3,
                    fontSize = 18.sp,
                    modifier = Modifier.clickable {
                        timePickerDialog.show()
                    })

                Spacer(modifier = Modifier.width(15.dp))

                Text(text = "Total: ${itemsState.value.total}",
                    style = MaterialTheme.typography.h2,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 20.dp))
            }


        }

        Spacer(modifier = Modifier.height(10.dp))

        //Header
        Box(modifier = Modifier
            .fillMaxWidth(listSize.width)
            .height(30.dp)
            ) {


            Canvas(modifier = Modifier.matchParentSize()){
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 3f
                )
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom) {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End){

                    //Description
                    Box(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(0.5f)){
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.h3,
                            fontSize = 17.sp,
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .padding(start = 10.dp))
                    }

                    //Qty
                    Box(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(0.30f)) {

                        Text(
                            text = "Qty",
                            style = MaterialTheme.typography.h3,
                            fontSize = 17.sp,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth())

                    }

                    //Subtotal Header
                    Box(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()){
                        Text(
                            text = "Subtotal",
                            style = MaterialTheme.typography.h3,
                            fontSize = 17.sp,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth())
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

            }

        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)) {

            val scrollState = rememberLazyListState()
            //@Todo Edit Height
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally){

                items(itemsState.value.items.size){ index ->

                    Item(
                        items = itemsState.value.items[index],
                        modifier = Modifier
                            .fillMaxWidth(listSize.width)
                            .height(listSize.height)
                            .clickable {
                                viewModel.onEvent(
                                    AddEditTransactionEvent.OnAddEditItem(
                                        index
                                    )
                                )
                            }
                            .background(Color.White),
                        OnDeleteItem = {viewModel.onEvent(AddEditTransactionEvent.OnDeleteItem(index))})
                }

                item {
                    AddItem(modifier = Modifier
                        .fillMaxWidth(listSize.width)
                        .height(listSize.height)
                        .background(Color.White)
                        .clickable { addItemDialog.show() })
                }
            }


            FloatingActionButton(
                onClick = {
                addItemDialog.show()},
                backgroundColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 10.dp, end = 10.dp)
                ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "add_item")

            }

        }


        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(60.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Color.Black)
                .clickable {
                    viewModel.onEvent(AddEditTransactionEvent.OnAddTransaction)
                }
        ) {

            Text(
                text ="Add",
                style = MaterialTheme.typography.h1,
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center))
        }

    }


}

object listSize{
    const val width = 0.95f
    val height = 50.dp
}