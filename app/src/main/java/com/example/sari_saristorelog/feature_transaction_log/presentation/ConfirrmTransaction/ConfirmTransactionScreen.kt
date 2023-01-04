package com.example.sari_saristorelog.feature_transaction_log.presentation.ConfirrmTransaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

@Composable
fun ConfirmTransactionScreen(
    navController: NavController,
    viewModel: ConfirmTransactionViewModel = hiltViewModel()
){

    val transactionInfoState = viewModel.transactionState.value?.transactionInfo
    val itemsState = viewModel.transactionState.value?.items
    val confirmTransactionState = viewModel.confirmTransactionState


    val dateTimeFormat = DateTimeFormatter.ofPattern("MMM, dd yyyy hh:mm")

    val doubleFormat = DecimalFormat("##.##")
    doubleFormat.roundingMode = RoundingMode.UP

    Box(modifier = Modifier
        .fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.95f),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(width = 2.dp, color = Color.Black, shape = CircleShape)
                ) {

                }

                Text(
                    text = transactionInfoState?.customerName ?: "",
                    style = MaterialTheme.typography.h2
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                Spacer(modifier = Modifier.fillMaxWidth(0.5f))

                Text(
                    text = "Total: " + doubleFormat.format(transactionInfoState?.total?: 0.0),
                    style = MaterialTheme.typography.h1 )

            }

            LazyColumn{
                itemsState?.let {
                    items(it){
                        Row() {
                            Text(text = it.description, modifier = Modifier.fillMaxWidth(0.4f))
                            Text(text = it.quantity.toString(), modifier = Modifier.fillMaxWidth(0.4f))
                            Text(text = doubleFormat.format(it.subtotal))
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }

        }

        Text(
            text = "Confirmed",
            color = if (confirmTransactionState.value.isConfirmed) Color.Black else Color.Transparent,
            style = MaterialTheme.typography.h1,
            fontSize = 75.sp,
            modifier = Modifier.rotate(-45f).align(Alignment.Center))


        Box(modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(0.7f)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.Black)
            .clickable(enabled = confirmTransactionState.value.isConfirmButtonEnable) {
                viewModel.onEvent(ConfirmTransactionEvent.OnConfirmed)
            }
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp)
            .shadow(elevation = 20.dp, clip = true)){
            Text(
                text = "Confirm",
                style = MaterialTheme.typography.h2.copy(color = Color.White),
                modifier = Modifier.align(Alignment.Center))
        }

    }


}