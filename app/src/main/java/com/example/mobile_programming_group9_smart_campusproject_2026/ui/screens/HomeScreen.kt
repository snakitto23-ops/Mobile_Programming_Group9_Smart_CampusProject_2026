package com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Ride(
    val driverName: String,
    val from: String,
    val to: String,
    val time: String,
    val seats: Int,
    val type: String, // "Offer" or "Request"
    val price: String,
    val extraInfo: String? = null,
    val phone: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val colorScheme = MaterialTheme.colorScheme
    val orangeColor = colorScheme.primary
    val navyColor = colorScheme.secondary
    val backgroundColor = colorScheme.background

    // Using only the 4 specified locations: Main Campus, Bombo, Luwero, Kampala Campus
    val rides = listOf(
        Ride("Alex", "Main Campus", "Kampala Campus", "5:00 PM", 3, "Offer", "10,000", phone = "0788117894"),
        Ride("Sarah", "Bombo", "Main Campus", "7:00 AM", 2, "Offer", "5,000", "Property Delivery Service", phone = "0788222333"),
        Ride("Henry", "Luwero", "Kampala Campus", "9:00 AM", 4, "Offer", "10,000", phone = "0777100243"),
        Ride("Edger", "Kampala Campus", "Bombo", "8:00 AM", 3, "Offer", "5,000", phone = "0700123456"),
        Ride("Kibogina", "Bombo", "Kampala Campus", "2:00 PM", 1, "Offer", "5,000", "Boda Boda", phone = "0755123456"),
        Ride("Musoke", "Main Campus", "Luwero", "10:00 AM", 0, "Offer", "7,000", "Property Delivery Service", phone = "0700999888"),
        Ride("Nakamya", "Kampala Campus", "Main Campus", "3:30 PM", 0, "Offer", "8,500", "Property Delivery Service", phone = "0755111222")
    )

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Ndejje University",
                            color = colorScheme.onPrimary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Available Rides",
                            color = colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("notifications") }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = orangeColor)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // My Profile Button (SafeBoda style)
            Button(
                onClick = { navController.navigate("profile") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = navyColor)
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = colorScheme.onSecondary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("My Profile", color = colorScheme.onSecondary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(rides) { ride ->
                    RideCard(ride, navController)
                }
            }
        }
    }
}

@Composable
fun RideCard(ride: Ride, navController: NavController) {
    val colorScheme = MaterialTheme.colorScheme
    val orangeColor = colorScheme.primary

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Pass all details including extraInfo to the details page
                val info = (ride.extraInfo ?: "none").replace(" ", "_")
                navController.navigate("details/${ride.driverName}/${ride.from}/${ride.to}/${ride.time}/${ride.phone}/${ride.price}/$info")
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Driver Icon
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.primaryContainer, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                CarLogo(Modifier.size(35.dp))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(ride.driverName, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = colorScheme.onSurface)
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(14.dp), tint = colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${ride.from} ➔ ${ride.to}", color = colorScheme.onSurfaceVariant, fontSize = 14.sp)
                }
                
                Text(
                    "Time: ${ride.time} | Seats: ${ride.seats}",
                    color = colorScheme.onSurfaceVariant,
                    fontSize = 13.sp
                )
                
                // Price Display
                Text(
                    "Price: UGX ${ride.price}",
                    color = orangeColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                
                ride.extraInfo?.let {
                    Text(it, color = Color(0xFFE91E63), fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }

            // Tag (Offer/Request)
            Box(
                modifier = Modifier
                    .background(
                        if (ride.type == "Offer") Color(0xFF4CAF50) else Color(0xFF2196F3),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(ride.type, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
