import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.tendo.snackwaves.PasswordInputField

//import androidx.compose.material3.ModalBottomSheet
//import androidx.compose.material3.Text
//import androidx.compose.material3.rememberModalBottomSheetState
//import androidx.compose.ui.window.DialogProperties
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.dialog
//import androidx.navigation.compose.rememberNavController
//import com.tendo.snackwaves.FoodDetailsScreen
//import com.tendo.snackwaves.LoginScreen
//import com.tendo.snackwaves.OrderConfirmationSheet
//import com.tendo.snackwaves.R
//import com.tendo.snackwaves.SignupScreen
//import com.tendo.snackwaves.WelcomeScreen
//import com.tendo.snackwaves.data.CartItem
//import com.tendo.snackwaves.data.CartScreen
//import com.tendo.snackwaves.data.OrderHistoryScreen
//import com.tendo.snackwaves.data.OrderItem
//
////package com.tendo.snackwaves
//
//fun FoodNavigation(onNextClick: () -> Unit) {
//    val navController = rememberNavController()
//    NavHost(navController, startDestination = "welcome") {
//        composable("welcome") {
//            WelcomeScreen(onNextClick = {
//                navController.navigate("login")
//                // Will update later to go to login screen
//            })
//        }
//        composable("login") {
//            LoginScreen(
//                onLoginClick = {
//                    navController.navigate("location"){
//                        popUpTo("login"){
//                            inclusive = true
//                        }
//                    }
//                    // Will update later to go to home/menu
//                },
//                onSignUpClick = {
//                    navController.navigate("signup")
//                }
//            )
//        }
//        // Placeholder for signup screen
//        composable("signup") {
//            Text("Sign Up Page (To be created next)")
//            SignupScreen(
//                onSignupClick = {
//                    // Will update later to go to home/menu
//                },
//                onBackToLoginClick = {
//                    navController.navigate("login")
//                },
//                navigate = Unit
//            )
//
//
//
//        }
//        composable("location") {
//            LocationScreen(
//                onContinueClick = {
//                    navController.navigate("home") // Replace with your next screen
//                }
//            )
//        }
//        composable("home") {
//            HomeScreenScaffold()
//        }
//
//        composable("FoodDetails") {
//            FoodDetailsScreen(
//                navController = navController
//            )
//        }
//
//        composable("cart") {
//            val sampleCart = listOf(
//                CartItem(1, "Hotdog", 450, 1, R.drawable.hunburger),
//                CartItem(2, "Pizza Slice", 300, 2, R.drawable.hunburger)
//            )
//            CartScreen(
//                cartItems = sampleCart,
//                onPlaceOrder = {
//                    navController.navigate("orderSheet")
//                },
//                navController = navController
//            )
//
//            // navController.navigate("orderSheet")
//
//        }
//
//        dialog("orderSheet", dialogProperties = DialogProperties(
//            usePlatformDefaultWidth = false,
//        )
//        ) {
//            // OrderConfirmationSheet(navController)
//            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//            ModalBottomSheet(
//                onDismissRequest = { navController.popBackStack() },
//                sheetState = sheetState,
//            ){
//                OrderConfirmationSheet(
//                    navController = navController,
//                    onDismiss = { navController.popBackStack() })
//
//            }
//
//
//        }
//
//        composable("history") {
//            val sampleOrders = listOf(
//                OrderItem(1, "Hunger Buzz", R.drawable.hunburger, 450, "Delivered", "June 19, 2025"),
//                OrderItem(2, "Cheesy Pizza", R.drawable.hunburger, 750, "In Progress", "June 18, 2025")
//            )
//            OrderHistoryScreen(orders = sampleOrders)
//        }
//
//
//
//    }
//}



//@Composable
//fun performRegistration() {
//    if (name.isBlank() || email.isBlank() || password.isBlank()) {
//        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
//        return
//    }
//    if (password != confirmPassword) {
//        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
//        return
//    }
//    // Basic email validation (optional, consider more robust validation)
//    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//        Toast.makeText(context, "Enter a valid email address", Toast.LENGTH_SHORT).show()
//        return
//    }
//    if (password.length < 6) {
//        Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
//        return
//    }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(
//                text = "Create Account",
//                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Bold
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            OutlinedTextField(
//                value = name,
//                onValueChange = { name = it },
//                label = { Text("Full Name") },
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                label = { Text("Email") },
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
////            OutlinedTextField(
////                value = password,
////                onValueChange = { password = it },
////                label = { Text("Password") },
////                visualTransformation = PasswordVisualTransformation(),
////                singleLine = true,
////                modifier = Modifier.fillMaxWidth()
////            )
//
//            PasswordInputField(
//                label = "Password",
//                password = password,
//                onPasswordChange = { password = it }
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            OutlinedTextField(
//                value = confirmPassword,
//                onValueChange = { confirmPassword = it },
//                label = { Text("Confirm Password") },
//                visualTransformation = PasswordVisualTransformation(),
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Button(
//                onClick = onSignupClick,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Sign Up")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            TextButton(onClick = onBackToLoginClick) {
//                Text("Already have an account? Login")
//            }
//        }
//    }
//}