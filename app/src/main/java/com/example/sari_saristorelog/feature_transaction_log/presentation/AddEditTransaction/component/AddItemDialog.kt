package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sari_saristorelog.ui.theme.Surface1

@Composable
fun AddItemDialog(
    description: String,
    quantity: TextFieldValue,
    price: TextFieldValue,
    subtotal: TextFieldValue,
    onDescriptionChange: (String) -> Unit,
    onQuantityChange: (TextFieldValue) -> Unit,
    onPriceChange: (TextFieldValue) -> Unit,
    onSubtotalChange: (TextFieldValue) -> Unit,
    backgroundColor: Color = Surface1,
){
    val dialogShape = RoundedCornerShape(10.dp)


    Column(horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .wrapContentHeight()) {
        
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Add Item",
            style = MaterialTheme.typography.h1,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 15.dp))

        Spacer(modifier = Modifier.height(20.dp))


        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.Start) {
            Text(text = "Description",
                style = MaterialTheme.typography.h3,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .wrapContentHeight())

            Spacer(modifier = Modifier.height(1.dp))

            DescriptionTextField(
                description = description,
                onValueChange = onDescriptionChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(start = 15.dp, end = 15.dp)
                    .clip(dialogShape)
                    .background(Color.White)
                    .border(width = 1.25.dp, color = Color.Black, shape = dialogShape))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth(0.5f)
                .wrapContentHeight()
                .padding(start = 5.dp, end = 5.dp)) {
                Text(
                    text = "Qty",
                    style = MaterialTheme.typography.h3,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 15.dp))

                NumberTextField(
                    value = quantity,
                    onValueChange = onQuantityChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .height(60.dp)
                        .clip(dialogShape)
                        .background(Color.White)
                        .border(width = 1.25.dp, color = Color.Black, shape = dialogShape))
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 5.dp, end = 5.dp)) {
                Text(
                    text = "Price",
                    style = MaterialTheme.typography.h3,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 15.dp))

                NumberTextField(
                    value = price,
                    onValueChange = onPriceChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .height(60.dp)
                        .clip(dialogShape)
                        .background(Color.White)
                        .border(width = 1.25.dp, color = Color.Black, shape = dialogShape))
            }

        }


        Spacer(modifier = Modifier.height(10.dp))

        Column(modifier = Modifier
            .fillMaxWidth(0.5f)
            .wrapContentHeight()
            .padding(start = 10.dp, end = 10.dp),
             horizontalAlignment = Alignment.Start) {
            Text(
                text = "Subtotal",
                style = MaterialTheme.typography.h3,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 15.dp))

            NumberTextField(
                value = subtotal,
                onValueChange = onSubtotalChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(dialogShape)
                    .background(Color.White)
                    .border(width = 1.25.dp, color = Color.Black, shape = dialogShape))
        }



    }

}