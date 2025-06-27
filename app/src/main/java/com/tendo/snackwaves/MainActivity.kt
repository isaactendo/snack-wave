package com.tendo.snackwaves

import android.Manifest
import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.tendo.snackwaves.ui.theme.SnackWavesTheme
//import com.tendo.foodnsnackwave.ui.theme.SnackWaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnackWavesTheme  {
               WelcomeScreenPreview(onNextClick = {
                   // TODO: Define what happens when "Next" is clicked in this preview
                   println("Next button clicked in WelcomeScreenPreview")
               } )
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
fun WelcomeScreenPreview(onNextClick: () -> Unit) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color.Red, Color.Yellow)
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .background(brush = gradient),
        contentAlignment = Alignment.BottomCenter
    ){

        Text(text ="Tasty WaveEats",
            color = Color.White,
            fontSize = 50.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center)


        // Row {
        //Image(
        //   painter = painterResource(id = R.drawable.yummyburger),
        //  contentDescription = "yummyburger",
        //  modifier = Modifier.size(120.dp)

        //)
        Image(
            painter = painterResource(id = R.drawable.tripplecheeseburger),
            contentDescription = "tripplecheeseburger",
            modifier = Modifier.offset(x = 1.dp)
                .size(320.dp)
                .padding(bottom = 1.dp)



        )
    }

}


@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
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

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
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

@Composable
fun FoodNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreenPreview(onNextClick = {
                navController.navigate("location")
            })
        }
        composable("login") {
            LoginScreen(
                onLoginClick = {
                    // Will update later to go to home/menu
                },
                onSignUpClick = {
                    navController.navigate("signup")
                }
            )
        }
        // Placeholder for signup screen
        composable("signup") {
            Text("Sign Up Page (To be created next)")
            SignupScreen(
                onSignupClick = {
                    // Will update later to go to home/menu
                },
                onBackToLoginClick = {
                    navController.navigate("login")
                },
                navigate = Unit
            )



        }
        composable("location") {
            LocationScreen(
                onContinueClick = {
                    navController.navigate("home") // Replace with your next screen
                }
            )
        }
    }
}

@Composable
fun SignupScreen(onSignupClick: () -> Unit, onBackToLoginClick: () -> Unit, navigate: Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onSignupClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onBackToLoginClick) {
                Text("Already have an account? Login")
            }
        }
    }
}

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
        onResult = @androidx.annotation.RequiresPermission(allOf = [android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION]) { granted ->
            if (granted) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        val geocoder = Geocoder(context, Locale.getDefault())
                        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
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


//@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,

        modifier = Modifier,
        bottomBar = {
            BottomAppBar(containerColor = Color.Red){
                Row(
                    Modifier,
                    horizontalArrangement = Arrangement.spacedBy(space = 80.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.homep),
                        contentDescription = null
                    )
                    Image(
                        painter = painterResource(id = R.drawable.contact),
                        contentDescription = null
                    )
                    Image(
                        painter = painterResource(id = R.drawable.chat),
                        contentDescription = null
                    )
                    Image(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = null
                    )
                }

            }
        },
        floatingActionButton = {
            Box (
                modifier = Modifier.fillMaxWidth().background(Color.Transparent),
                contentAlignment = Alignment.Center

            ){

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
        Box(modifier = Modifier.padding(it)){
            HomeScreen()

        }
    }

}


//@Preview(showBackground = true)
@Composable
fun FooditemPreview() {
    Card(
        modifier = Modifier
            .width(width = 200.dp)
            .height(height = 230.dp),
        shape = RoundedCornerShape(size = 20.dp),
        //  colors = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.hunburger),
            contentDescription = "hunburger",
            modifier = Modifier
                .padding(all = 20.dp)
                .size(100.dp)
        )
        Column(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        ){
            Text(text = "Hunger Buzz", Modifier, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "Wendy's Hotdog", Modifier, fontSize = 16.sp, fontWeight = FontWeight.Normal)

        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RatingDataPreview()
            Spacer(modifier = Modifier.weight(weight = 1f))
            Image(painter = painterResource(id = R.drawable.love),
                contentDescription = null,
                modifier = Modifier.padding(top = 5.dp, end = 5.dp) ,
            )
           // Text(text = "4.5", Modifier, fontSize = 16.sp, fontWeight = FontWeight.Normal)
        }

    }

}

//@Preview(showBackground = true)
@Composable
fun RatingDataPreview() {
    Row(
        modifier = Modifier.padding(top = 16.dp, start = 14.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "star",
            modifier = Modifier.size(16.dp)
        )
        Text(text = "4.5", Modifier, fontSize = 16.sp, fontWeight = FontWeight.Normal)

    }
}

//@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Text(text = "Tasty WaveEats", Modifier, fontSize = 28.sp, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic)
        Text(text = "Order your favorite food and snack ", Modifier, fontSize = 14.sp,
            color = Color(color = 0xFF707070),
            fontWeight = FontWeight.Normal)
        SearchBox()
        HorizontalSliderItems()
        HomeScreenPreview()

        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(space = 11.dp),
            verticalArrangement = Arrangement.spacedBy(space = 11.dp),
            modifier = Modifier.fillMaxSize().padding(vertical = 10.dp),
            columns = GridCells.Fixed(count = 2),
          //  contentPadding = PaddingValues(all = 20.dp),
        ) {
            items( count = 10) {
                FooditemPreview()
            }
        }


    }
}

//@Preview(showBackground = true)
@Composable
fun HorizontalSliderItems(){
    val text = listOf<SlidingContent>(
        SlidingContent("All", selected = true),
        SlidingContent("Combos", selected = false),
        SlidingContent("Pizza" , selected = false),
        SlidingContent("Burgers" , selected = false),
        SlidingContent("Snacks" , selected = false))
      // SlidingContent("Beverages" , selected = false))
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth().
        wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
        //verticalAlignment = Alignment.CenterVertically

        ) {
        text.forEach {
            Box(
                modifier = Modifier.height(height = 30.dp).wrapContentWidth()
                    .background(
                        color = if (it.selected)Color.Red else Color.Transparent,
                        shape = RoundedCornerShape(size = 16.dp))
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = it.text,
                    Modifier,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (it.selected)Color.White else Color.Black
                )
            }
        }
    }
}
data class SlidingContent(
    val text: String,
    val selected : Boolean = false
)

@Composable
fun SearchBox(){
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Row(
            modifier = Modifier.height(height = 60.dp)
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
                .padding(top = 10.dp, )
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