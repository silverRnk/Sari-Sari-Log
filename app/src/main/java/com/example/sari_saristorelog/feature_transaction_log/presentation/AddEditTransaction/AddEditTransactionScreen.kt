package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.AddItem
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.CustomerInfoFill
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component.Item

@Composable
fun AddEditTransactionScreen(
    navController: NavController,
    viewModel: AddEditTransactionViewModel = hiltViewModel()
){

    val itemsState = viewModel.itemState
    val customerInfoState = viewModel.customerInfoState


    LaunchedEffect(key1 = 1){
        //TODO add navigation
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
            Text(text = "Total: ${itemsState.value.total}",
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterEnd)
                .padding(end = 20.dp))
        }

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
                            fontSize = 15.sp,
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
                            fontSize = 15.sp,
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
                            fontSize = 15.sp,
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
                        .clickable { viewModel.onEvent(AddEditTransactionEvent.OnAddEditItem()) })
                }
            }


            FloatingActionButton(
                onClick = {
                viewModel.onEvent(AddEditTransactionEvent.OnAddEditItem())},
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
                .height(75.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Color.Black)
                .clickable {
                    viewModel.onEvent(AddEditTransactionEvent.OnAddTransaction)
                }
        ) {

            Text(
                text ="Add",
                style = MaterialTheme.typography.h1,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center))
        }

    }


}

object listSize{
    const val width = 0.95f
    val height = 50.dp
}