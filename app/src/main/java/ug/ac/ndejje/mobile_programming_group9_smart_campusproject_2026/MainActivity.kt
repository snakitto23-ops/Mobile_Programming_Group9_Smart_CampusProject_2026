
package com.example.smartcampusride

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

data class User(
    val name: String,
    val email: String,
    val role: String, // Student or Driver
    val phone: String,
    val imageUri: Uri?
)

var currentUser by mutableStateOf<User?>(null)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("details") { RideDetailsScreen(navController) }
        composable("tracking") { TrackingScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
    }
}

// ---------------- LOGIN SCREEN ----------------
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Login")

        TextField(value = email, onValueChange = { email = it }, label = { Text("School Email") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

        Button(onClick = { navController.navigate("home") }) {
            Text("Login")
        }

        TextButton(onClick = { navController.navigate("register") }) {
            Text("Register")
        }
    }
}

// ---------------- REGISTER SCREEN ----------------
@Composable
fun RegisterScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("Student") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Registration")

        TextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("School Email") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        TextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone Number") })

        Spacer(modifier = Modifier.height(10.dp))

        // ROLE SELECT
        Row {
            Button(onClick = { role = "Student" }) { Text("Student") }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { role = "Driver" }) { Text("Driver") }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // IMAGE PREVIEW (placeholder only)
        Text("Profile Image")
        Image(
            painter = painterResource(android.R.drawable.ic_menu_gallery),
            contentDescription = "Profile",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            currentUser = User(name, email, role, phone, imageUri)
            navController.navigate("home")
        }) {
            Text("Register")
        }
    }
}

// ---------------- HOME SCREEN ----------------
@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Available Rides")

        Button(onClick = { navController.navigate("details") }) {
            Text("Alex - Campus to Kampala")
        }

        Button(onClick = { navController.navigate("details") }) {
            Text("Sarah - Bombo to Campus")
        }

        Button(onClick = { navController.navigate("profile") }) {
            Text("Go to Profile")
        }
    }
}

// ---------------- RIDE DETAILS ----------------
@Composable
fun RideDetailsScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Driver: Lomeling")
        Text("Rating: 4.5")
        Text("From: Campus")
        Text("To: Kampala")
        Text("Phone: 0788117894")

        Button(onClick = { navController.navigate("tracking") }) {
            Text("Track Ride")
        }
    }
}

// ---------------- TRACKING SCREEN ----------------
@Composable
fun TrackingScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tracking Ride...")
        Text("Driver is on the way")
    }
}

// ---------------- PROFILE SCREEN ----------------
@Composable
fun ProfileScreen(navController: NavController) {
    val user = currentUser

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Profile")

        if (user != null) {
            Image(
                painter = painterResource(android.R.drawable.ic_menu_gallery),
                contentDescription = "Profile",
                modifier = Modifier.size(100.dp)
            )

            Text("Name: ${user.name}")
            Text("Email: ${user.email}")
            Text("Role: ${user.role}")
            Text("Phone: ${user.phone}")
        } else {
            Text("No user data")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { navController.navigate("login") }) {
            Text("Logout")
        }
    }
}