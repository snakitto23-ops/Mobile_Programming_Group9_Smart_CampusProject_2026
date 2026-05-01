package com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BookingSuccessScreen(
    navController: NavController,
    from: String,
    to: String,
    price: String
) {
    val colorScheme = MaterialTheme.colorScheme
    val orangeColor = colorScheme.primary
    val navyColor = colorScheme.secondary

    var isCancelling by remember { mutableStateOf(false) }
    var cancellationReason by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!isCancelling) {
            // Success Icon
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(0xFF4CAF50), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Success",
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Booking Successful!",
                style = MaterialTheme.typography.headlineMedium,
                color = colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Journey",
                        style = MaterialTheme.typography.labelLarge,
                        color = colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "$from \u2794 $to",
                        style = MaterialTheme.typography.titleLarge,
                        color = navyColor,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Total Amount",
                        style = MaterialTheme.typography.labelLarge,
                        color = colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "UGX $price",
                        style = MaterialTheme.typography.headlineMedium,
                        color = orangeColor,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = navyColor)
            ) {
                Text("Back to Home", color = colorScheme.onSecondary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { isCancelling = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red)
            ) {
                Text("Cancel Ride", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        } else {
            // Cancellation UI
            Text(
                text = "Cancel Your Ride",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "We're sorry to see you go. Please tell us why you are cancelling:",
                textAlign = TextAlign.Center,
                color = colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = cancellationReason,
                onValueChange = { cancellationReason = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = { Text("Reason for cancellation (e.g. Changed my mind, found another ride)") },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = colorScheme.outline
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Logic to handle cancellation would go here
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                enabled = cancellationReason.isNotBlank()
            ) {
                Text("Confirm Cancellation", color = Color.White, fontWeight = FontWeight.Bold)
            }

            TextButton(
                onClick = { isCancelling = false },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Keep My Ride", color = navyColor, fontWeight = FontWeight.Medium)
            }
        }
    }
}
