package com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RideDetailsScreen(
    navController: NavController,
    name: String,
    from: String,
    to: String,
    time: String,
    phone: String,
    price: String
) {
    val orangeColor = Color(0xFFFF9800)
    val navyColor = Color(0xFF0D3B66)
    val lightBeige = Color(0xFFFAF7F2)
    val iconBgColor = Color(0xFFFFF3E0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Details Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = lightBeige),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DetailItem(icon = Icons.Default.Person, label = "Driver", value = name, valueColor = navyColor, iconTint = orangeColor, iconBg = iconBgColor)
                DetailItem(icon = Icons.Default.Star, label = "Rating", value = "4.5", valueColor = navyColor, iconTint = orangeColor, iconBg = iconBgColor, isRating = true)
                DetailItem(icon = Icons.Default.LocationOn, label = "From", value = from, valueColor = navyColor, iconTint = orangeColor, iconBg = iconBgColor)
                DetailItem(icon = Icons.Default.LocationOn, label = "To", value = to, valueColor = navyColor, iconTint = orangeColor, iconBg = iconBgColor)
                DetailItem(icon = Icons.Default.Info, label = "Time", value = time, valueColor = navyColor, iconTint = orangeColor, iconBg = iconBgColor)
                DetailItem(icon = Icons.Default.Phone, label = "Phone", value = phone, valueColor = navyColor, iconTint = orangeColor, iconBg = iconBgColor)
                DetailItem(icon = Icons.Default.Info, label = "Price", value = "UGX $price", valueColor = orangeColor, iconTint = orangeColor, iconBg = iconBgColor)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        Button(
            onClick = { navController.navigate("tracking") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = navyColor)
        ) {
            Icon(Icons.Default.Place, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Track Ride", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { 
                navController.navigate("price-review/$from/$to/$price")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = orangeColor)
        ) {
            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Confirm Request", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { 
                navController.navigate("call/$name/$phone")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color.Gray),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = orangeColor)
        ) {
            Icon(Icons.Default.Phone, contentDescription = null, tint = orangeColor)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Call User", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun DetailItem(
    icon: ImageVector,
    label: String,
    value: String,
    valueColor: Color,
    iconTint: Color,
    iconBg: Color,
    isRating: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(iconBg, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(24.dp))
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column {
            Text(label, color = Color.Gray, fontSize = 12.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isRating) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = iconTint, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text(value, color = valueColor, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
