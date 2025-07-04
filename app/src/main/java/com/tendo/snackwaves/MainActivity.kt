package com.tendo.snackwaves

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.tendo.snackwaves.data.CartItem
import com.tendo.snackwaves.data.CartScreen
import com.tendo.snackwaves.data.OrderHistoryScreen
import com.tendo.snackwaves.data.OrderItem
import com.tendo.snackwaves.ui.theme.SnackWavesTheme
import java.util.Locale

//import com.tendo.foodnsnackwave.ui.theme.SnackWaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnackWavesTheme {
                FoodNavigation(onNextClick = {
                    // TODO: Define what happens when "Next" is clicked in this preview
                    // println("Next button clicked in WelcomeScreen")
                }
                )
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
                //                            .padding(innerPadding)
//                    ) {


            }
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun WelcomeScreen(onNextClick: () -> Unit) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color.Red, Color.Yellow)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Tasty WaveEats",
                color = Color.White,
                fontSize = 44.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.tripplecheeseburger),
                contentDescription = "tripplecheeseburger",
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 20.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = onNextClick,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .height(50.dp)
            ) {
                Text("Get Started", color = Color.Red, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun HomeScreenScaffold(navController: NavHostController) {
    Scaffold(
        modifier = Modifier,
        floatingActionButtonPosition = FabPosition.Center,
//        isFloatingActionButtonDocked = true, // This parameter is not available in Material3 Scaffold
        bottomBar = {
            BottomAppBar(containerColor = Color.Red) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* TODO */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.homep),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /* TODO */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.contact),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /* TODO */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.chat),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /* TODO */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = null
                        )
                    }
                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Navigate to Cart or Add */ },
                containerColor = Color.Red,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center

            ) {

                Box(
                    modifier = Modifier
                        .offset(y = 3.dp)
                        .size(size = 50.dp)
                        .clip(CircleShape)
                        // .padding(start = 0.dp)
                        .background(Color.Red),
                    //  contentAlignment = Alignment.Center

                )


                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    Modifier.size(size = 40.dp),
                    tint = Color.White
                )

            }
        }

    ) {
        Box(modifier = Modifier.padding(it)) {
            HomeScreen(
                onFoodItemClick = {},
                onCartClick = {},
                onHistoryClick = {},
                modifier = Modifier.padding(it)
            )

        }
    }

}

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    navController: NavHostController,
    auth: FirebaseAuth?=null
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Password") },
//                visualTransformation = PasswordVisualTransformation(),
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth()
//            )
            PasswordInputField(
                label = "Password",
                password = password,
                onPasswordChange = { password = it }
            )



            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onSignUpClick) {
                Text("Don't have an account? Sign up")
            }
        }
    }
}

// Update Navigation in MainActivity.kt

//Replace your previous NavHost with this:

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodNavigation(onNextClick: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(onNextClick = {
                navController.navigate("login")
                // Will update later to go to login screen
            })
        }
        composable("login") {
            LoginScreen(
                navController = navController,
                onLoginClick = {

                    navController.navigate("location") {
                        popUpTo("login") {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                    // Will update later to go to home/menu
                },
                onSignUpClick = {
                    navController.navigate("signup")
                }
            )
        }
        // Placeholder for signup screen
        composable("signup") {
          //  Text("Sign Up Page (To be created next)")
            SignupScreen(
                auth = Firebase.auth,
                database = Firebase.database,
                navController = navController,
                onBackToLoginClick = {
                    navController.popBackStack()
                }
             //   navigate = Unit,
              //  onSignupClick = {
                 //   navController.navigate("location")
            //    }

            )
        }
        composable("location") {
            LocationScreen(
                onContinueClick = {
                    navController.navigate("home") // Replace with your next screen
                }
            )
        }
        composable("home") {
            HomeScreenScaffold(navController = navController)
        }

        composable("FoodDetails") {
            FoodDetailsScreen(
                navController = navController
            )
        }

        composable("cart") {
            val sampleCart = listOf(
                CartItem(1, "Hotdog", 450, 1, R.drawable.hunburger),
                CartItem(2, "Pizza Slice", 300, 2, R.drawable.hunburger)
            )
            CartScreen(
                cartItems = sampleCart,
                onPlaceOrder = {
                    navController.navigate("orderSheet")
                },
                navController = navController
            )

            // navController.navigate("orderSheet")

        }

        dialog(
            "orderSheet", dialogProperties = DialogProperties(
                usePlatformDefaultWidth = false,
            )
        ) {
            // OrderConfirmationSheet(navController)
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            ModalBottomSheet(
                onDismissRequest = { navController.popBackStack() },
                sheetState = sheetState,
            ) {
                OrderConfirmationSheet(
                    navController = navController,
                    onDismiss = { navController.popBackStack() })

            }


        }

        composable("history") {
            val sampleOrders = listOf(
                OrderItem(
                    1,
                    "Hunger Buzz",
                    R.drawable.hunburger,
                    450,
                    "Delivered",
                    "June 19, 2025"
                ),
                OrderItem(
                    2,
                    "Cheesy Pizza",
                    R.drawable.hunburger,
                    750,
                    "In Progress",
                    "June 18, 2025"
                )
            )
            OrderHistoryScreen(orders = sampleOrders)
        }


    }
}

@Composable
fun PasswordInputField(
    label: String,
    password: String,
    onPasswordChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (passwordVisible) Icons.Default.Add else Icons.Default.Search
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = icon,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun FoodDetailsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.hunburger),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Hunger Buzz", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("Wendy’s Hotdog", fontSize = 16.sp, color = Color.Gray)
            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                RatingDataPreview()
                Spacer(Modifier.width(16.dp))
                Text("4.5 (250 reviews)", fontSize = 14.sp, color = Color.DarkGray)
            }

            Spacer(Modifier.height(16.dp))

            Text(
                "A delicious hotdog served with melted cheese, ketchup, mustard, and crispy onions in a fresh bun.",
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(Modifier.height(24.dp))

            Text("Price: KES 450", fontSize = 18.sp, fontWeight = FontWeight.Medium)

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate("Cart")/* TODO: Add to cart */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Add to Cart", color = Color.White)
            }
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun LocationScreen(
    context: Context = LocalContext.current,
    onContinueClick: () -> Unit
) {
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var locationText by remember { mutableStateOf("Fetching location...") }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION]) { granted ->
            if (granted) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        val geocoder = Geocoder(context, Locale.getDefault())
                        val addresses =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        if (!addresses.isNullOrEmpty()) {
                            locationText = addresses[0].getAddressLine(0)
                        } else {
                            locationText = "Unable to get address."
                        }
                    } else {
                        locationText = "Location not available."
                    }
                }
            } else {
                locationText = "Permission denied."
            }
        }
    )

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Your Current Location:",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = locationText,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onContinueClick,
                enabled = !locationText.contains("Fetching"),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue")
            }
        }
    }
}

@Composable
fun SignupScreen(
    navController: NavController,
    onBackToLoginClick: () -> Unit,
    auth: FirebaseAuth,
    database: FirebaseDatabase,
//    onSignupClick: NavHostController,
//    onBackToLoginClick: () -> Unit,
//    navigate: Unit,
//    navController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }


    val context = LocalContext.current
    val auth: FirebaseAuth = remember { Firebase.auth }
    val database: FirebaseDatabase = remember { Firebase.database }


    // This is a regular (non-Composable) function.
    // It can access `navController` because it's defined within the scope
    // of SignupScreen, where navController is a parameter.
    // NOT @Composable
    fun performRegistration() {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmPassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "Enter a valid email address", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.length < 6) {
            Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }

        isLoading = true
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoading = false
                if (task.isSuccessful) {
                    Toast.makeText(context, "Signup Successful", Toast.LENGTH_SHORT).show()
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        val userProfile = mapOf("name" to name, "email" to email)
                        database.getReference("users").child(userId).setValue(userProfile)
                    }
                    navController.navigate("location") { // Or "home"
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                } else {
                    Toast.makeText(context, "Signup Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    // --- Start of UI ---
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField( /* ... Name ... */ value = name, onValueChange = { name = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField( /* ... Email ... */ value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            PasswordInputField(
                label = "Password",
                password = password,
                onPasswordChange = { password = it }
            )
            Spacer(modifier = Modifier.height(12.dp))
            PasswordInputField( // Use PasswordInputField for confirm password too
                label = "Confirm Password",
                password = confirmPassword,
                onPasswordChange = { confirmPassword = it }
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { performRegistration() }, // CALL THE LOCAL FUNCTION
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Sign Up")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { if (!isLoading) onBackToLoginClick() }) {
                Text("Already have an account? Login")
            }
        }
    }
    // This was the closing brace for performRegistration(), which is incorrect.
    // The UI should be outside performRegistration() if performRegistration is a helper function.
    // If performRegistration was meant to BE the composable content, the structure is very different.
    // I've assumed performRegistration is a helper function called by the button.
}

  

    //@Preview(showBackground = true)
    @Composable
    fun HorizontalSliderItems() {
        val text = listOf<SlidingContent>(
            SlidingContent("All", selected = true),
            SlidingContent("Combos", selected = false),
            SlidingContent("Pizza", selected = false),
            SlidingContent("Burgers", selected = false),
            SlidingContent("Snacks", selected = false)
        )
        // SlidingContent("Beverages" , selected = false))
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
            //verticalAlignment = Alignment.CenterVertically

        ) {
            text.forEach {
                Box(
                    modifier = Modifier
                        .height(height = 30.dp)
                        .wrapContentWidth()
                        .background(
                            color = if (it.selected) Color.Red else Color.Transparent,
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.text,
                        Modifier,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (it.selected) Color.White else Color.Black
                    )
                }
            }
        }
    }

    data class SlidingContent(
        val text: String,
        val selected: Boolean = false
    )

    @Composable
    fun SearchBox() {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Row(
                modifier = Modifier
                    .height(height = 60.dp)
                    .padding(top = 8.dp)
                    .width(width = 300.dp)
                    .clip(shape = RoundedCornerShape(size = 10.dp))
                    .background(Color.Transparent)
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
                //verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(Icons.Filled.Search, contentDescription = null)
                Text(
                    text = "Search",
                    Modifier,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(size = 50.dp)
                    .background(color = Color.Red, shape = RoundedCornerShape(size = 10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nav),
                    contentDescription = null,
                    // modifier = Modifier.size(120.dp)
                )
            }
        }

    }




@Composable
fun FooditemPreview(
    foodItemId: String, // Example: To display item-specific data
    onItemClick: () -> Unit // Changed from NavController
) {
    Card(
        modifier = Modifier
            .width(width = 180.dp)
            .height(height = 240.dp)
            .clickable { onItemClick() }, // Use the lambda
        shape = RoundedCornerShape(size = 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.hunburger), // Replace with actual item image
            contentDescription = "Food item image", // More descriptive
            modifier = Modifier
                .padding(all = 20.dp)
                .size(120.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        ) {
            Text(
                text = "Hunger Buzz $foodItemId", // Display item-specific data
                modifier = Modifier, // Correct named parameter
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Wendy's Hotdog",
                modifier = Modifier, // Correct named parameter
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(), // Allow row to fill width for spacing
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // For spacing rating and heart
        ) {
            RatingDataPreview(modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)) // Add padding
            Image(
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Favorite",
                modifier = Modifier.padding(top = 5.dp, end = 16.dp, bottom = 8.dp).size(24.dp), // Add padding and size
            )
        }
    }
}


@Composable
fun RatingDataPreview(modifier: Modifier = Modifier) { // Accept modifier
    Row(
        modifier = modifier, // Use passed modifier
        verticalAlignment = Alignment.CenterVertically // Align items in the row
    ) {
        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "star rating",
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp)) // Space between star and text
        Text(
            text = "4.5",
            // modifier = Modifier, // Not needed if no specific mods for text itself
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun HomeScreen(
onFoodItemClick: (foodItemId: String) -> Unit,
onCartClick: () -> Unit,
onHistoryClick: () -> Unit,
modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier // Use the passed modifier from Scaffold
            .fillMaxSize()
            .padding(horizontal = 20.dp) // Keep additional padding if needed, or rely on Scaffold's
    ) {
        Text(
            text = "Tasty WaveEats",
            modifier = Modifier, // Correct named parameter
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "Order your favorite food and snack ",
            modifier = Modifier, // Correct named parameter
            fontSize = 14.sp,
            color = Color(color = 0xFF707070),
            fontWeight = FontWeight.Normal
        )
        SearchBox()
        HorizontalSliderItems()

        // Example: If you need a direct history button here too
        // Button(onClick = onHistoryClick) { Text("Order History") }

        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(space = 11.dp),
            verticalArrangement = Arrangement.spacedBy(space = 11.dp),
            modifier = Modifier
                .fillMaxSize() // This might be too much if SearchBox and HorizontalSliderItems are above
                .weight(1f) // Allow grid to take remaining space
                .padding(vertical = 10.dp),
            columns = GridCells.Fixed(count = 2),
        ) {
            val foodItems = List(10) { index -> "food_item_$index" } // Dummy data
            items(items = foodItems, key = { it }) { foodItemId -> // Use items(list, key)
                FooditemPreview(
                    foodItemId = foodItemId, // Pass data if needed by FooditemPreview for display
                    onItemClick = { onFoodItemClick(foodItemId) } // Call the lambda
                )
            }
        }
        Button( // This button might be better placed in a Scaffold bottom bar or top app bar
            onClick = onCartClick,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("View Cart")
        }
    }
}

