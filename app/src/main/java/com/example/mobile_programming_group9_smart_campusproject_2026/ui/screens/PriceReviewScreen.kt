package com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceReviewScreen(
    navController: NavController,
    from: String,
    to: String,
    price: String
) {
    val orangeColor = Color(0xFFFF9800)
    val navyColor = Color(0xFF0D3B66)
    val backgroundColor = Color(0xFFF5F5F5)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Price Review", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = orangeColor)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            
            // Route Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Your Journey", color = Color.Gray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(from, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = navyColor)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 8.dp),
                            tint = orangeColor
                        )
                        Text(to, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = navyColor)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Price Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Wallet, contentDescription = null, tint = orangeColor, modifier = Modifier.size(40.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Total Fare", color = Color.Gray, fontSize = 16.sp)
                    Text("UGX $price", fontWeight = FontWeight.ExtraBold, fontSize = 32.sp, color = orangeColor)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Payment Method: Cash on Ride", color = navyColor, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { 
                    navController.navigate("success/$from/$to/$price")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = navyColor)
            ) {
                Text("Confirm Booking", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            
            TextButton(onClick = { navController.popBackStack() }) {
                Text("Cancel", color = Color.Gray)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
