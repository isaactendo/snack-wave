package com.tendo.snackwaves.data

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


data class CartItem(
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
    val image: Int
)

@Composable
fun CartScreen(
    navController: NavController,
    cartItems: List<CartItem>,
    onPlaceOrder: () -> Unit
) {
    var data by remember { mutableStateOf(cartItems) }

    val totalPrice = data.sumOf { it.price * it.quantity }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Your Cart", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(data){
                item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = item.image),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(item.name, fontWeight = FontWeight.Bold)
                            Text("KES ${item.price}", color = Color.Gray)
                        }
                        Row {
                            IconButton(onClick = {
                                data = data.map {
                                    if (it.id == item.id && it.quantity > 1)
                                        it.copy(quantity = it.quantity - 1)
                                    else it
                                }
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Remove")
                            }
                            Text(item.quantity.toString(), modifier = Modifier.padding(4.dp))
                            IconButton(onClick = {
                                data = data.map {
                                    if (it.id == item.id)
                                        it.copy(quantity = it.quantity + 1)
                                    else it
                                }
                            }) {
                                Icon(Icons.Default.Add, contentDescription = "Add")
                            }
                    }
                }
            }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Total: KES $totalPrice", fontSize = 18.sp, fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                navController.navigate("orderSheet")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Place Order", color = Color.White)
        }
    }
}
