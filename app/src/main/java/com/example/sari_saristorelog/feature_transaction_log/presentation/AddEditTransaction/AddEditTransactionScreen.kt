
package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction

import androidx.compose.animation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sari_saristorelog.R
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.AddItem
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.AddItemDialog
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.CustomerInfoForm
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.Item
import com.example.sari_saristorelog.feature_transaction_log.presentation.util.PickerColor
import com.example.sari_saristorelog.ui.theme.Surface1
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

const val ICON_HORIZONTAL_SPACING = 30

@Composable
fun AddEditTransactionScreen(
    navController: NavController,
    viewModel: AddEditTransactionViewModel = hiltViewModel()
){
    val dialogShape = RoundedCornerShape(10.dp)

    val itemsState = viewModel.itemState
    val customerInfoState = viewModel.customerInfoState
    val dateState = viewModel.dateState
    val addItemDialogState = viewModel.addItemDialogState

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
        shape = dialogShape,
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
        shape = dialogShape,
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


    MaterialDialog(
        dialogState = addItemDialog,
        backgroundColor = Surface1,
        shape = dialogShape,
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = MaterialTheme.typography.h1.copy(fontSize = 15.sp, color = Color.Black),
                onClick = {
                    viewModel.dialogEvent(AddEditDialogEvent.OnPositiveButton)
                }
            )

            negativeButton(
                text = "Cancel",
                textStyle = MaterialTheme.typography.h1.copy(fontSize = 15.sp,color = Color.Black),
                onClick = {viewModel.dialogEvent(AddEditDialogEvent.OnCancel)})
        }
    ) {


        //Todo
        AddItemDialog(
            description = addItemDialogState.value.description,
            quantity = addItemDialogState.value.quantity,
            price = addItemDialogState.value.price,
            subtotal = addItemDialogState.value.subtotal,
            onDescriptionChange = {viewModel.dialogEvent(AddEditDialogEvent.OnDescriptionChange(it))},
            onQuantityChange = { viewModel.dialogEvent(AddEditDialogEvent.OnQuantityChange(it)) },
            onPriceChange = {viewModel.dialogEvent(AddEditDialogEvent.OnPriceChange(it)) },
            onSubtotalChange = { viewModel.dialogEvent(AddEditDialogEvent.OnSubtotalChange(it)) }
        )

    }



    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(10.dp))

        CustomerInfoForm(
            customerIcon = customerInfoState.value.customerIcon,
            customerName = customerInfoState.value.name,
            onNameChange = {viewModel.onEvent(AddEditTransactionEvent.OnNameTextFieldChange(it))},
            onToggleIconSelection = {viewModel.onEvent(AddEditTransactionEvent.OnToggleIconSelection)},
            modifier = Modifier
                .fillMaxWidth(listSize.width + 0.02f)
                .wrapContentHeight())

        Spacer(modifier = Modifier.height(10.dp))

        //Total
        Box(modifier = Modifier
            .fillMaxWidth(listSize.width)
            .wrapContentHeight()){

            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {

                val iconsModifier = Modifier.size(28.dp)

                Box(modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(5.dp))
                    .clickable { datePickerDialog.show() }
                    ){
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "calendar",
                        modifier = iconsModifier)
                }

                Spacer(modifier = Modifier.width(ICON_HORIZONTAL_SPACING.toDouble().dp))

                Box(modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(5.dp))
                    .clickable { timePickerDialog.show() }
                ){
                    Icon(painter = painterResource(id = R.drawable.ic_time_24),
                        contentDescription = "Time",
                        modifier = iconsModifier)
                }

                Spacer(modifier = Modifier.width(ICON_HORIZONTAL_SPACING.toDouble().dp))

                Box(modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(5.dp))
                    .clickable { timePickerDialog.show() }
                ){
                    Icon(painter = painterResource(id = R.drawable.ic_refresh_24),
                        contentDescription = "refresh",
                        modifier = iconsModifier)
                }

                Spacer(modifier = Modifier.width(ICON_HORIZONTAL_SPACING.toDouble().dp))

                Box(modifier = Modifier
                    .wrapContentSize()
                    .clip(CircleShape)
                    .background(if (dateState.value.isVisible) Color.LightGray else Color.Transparent)
                    .clickable { viewModel.onEvent(AddEditTransactionEvent.OnToggleDate) }
                ){
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_down_24),
                        contentDescription = "arrow_down",
                        modifier = iconsModifier)
                }

            }

        }

        //Todo add visibleState
        AnimatedVisibility(
            visible = dateState.value.isVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
        ) {

            Box(modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
                .background(Color.Gray)){
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = dateFormatter.value,
                        style = MaterialTheme.typography.h3,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = timeFormatter.value,
                        style = MaterialTheme.typography.h3,
                        fontSize = 18.sp,
                        )}
            }

        }

        Box(modifier = Modifier
            .fillMaxWidth(listSize.width)
            .wrapContentHeight()){

            val doubleFormatter = DecimalFormat("#.##")
            doubleFormatter.roundingMode = RoundingMode.DOWN
            val roundedTotal = doubleFormatter.format(itemsState.value.total)

            BasicText(
                text = "Total: $roundedTotal",
                style = MaterialTheme.typography.h2.copy(fontSize = 30.sp),
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.CenterStart))
        }

        Spacer(modifier = Modifier.height(5.dp))

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
                    .fillMaxHeight(),
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