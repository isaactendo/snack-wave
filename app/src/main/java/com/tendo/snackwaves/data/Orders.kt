package com.tendo.snackwaves.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class OrderItem(
    val id: Int,
    val name: String,
    val image: Int,
    val price: Int,
    val status: String,
    val date: String
)

@Composable
fun OrderHistoryScreen(
    orders: List<OrderItem>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Order History", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(orders) { order ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = order.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(order.name, fontWeight = FontWeight.Bold)
                            Text("KES ${order.price}", color = Color.Gray)
                            Text(order.date, fontSize = 12.sp, color = Color.Gray)
                        }

                        Text(
                            text = order.status,
                            color = if (order.status == "Delivered") Color(0xFF4CAF50) else Color(0xFFFF9800),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

//
//@Composable
//fun WelcomeScreen(onNextClick: () -> Unit) {
//    val gradient = Brush.linearGradient(
//        colors = listOf(Color.Red, Color.Yellow)
//    )
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(brush = gradient),
//        contentAlignment = Alignment.BottomCenter
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 100.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Text(
//                text = "Tasty WaveEats",
//                color = Color.White,
//                fontSize = 44.sp,
//                fontWeight = FontWeight.SemiBold,
//                fontStyle = FontStyle.Italic,
//                textAlign = TextAlign.Center
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Image(
//                painter = painterResource(id = R.drawable.tripplecheeseburger),
//                contentDescription = "tripplecheeseburger",
//                modifier = Modifier
//                    .size(300.dp)
//                    .padding(top = 20.dp)
//            )
//
//            Spacer(modifier = Modifier.height(50.dp))
//
//            Button(
//                onClick = onNextClick,
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
//                modifier = Modifier
//                    .padding(horizontal = 32.dp)
//                    .height(50.dp)
//            ) {
//                Text("Get Started", color = Color.Red, fontWeight = FontWeight.Bold)
//            }
//        }
//    }
//}

