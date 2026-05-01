package com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    price: String,
    extraInfo: String = "none"
) {
    val colorScheme = MaterialTheme.colorScheme
    val orangeColor = colorScheme.primary
    val navyColor = colorScheme.secondary
    val backgroundColor = colorScheme.background
    val surfaceColor = colorScheme.surface
    val iconBgColor = colorScheme.primaryContainer

    val isDelivery = extraInfo.contains("Delivery", ignoreCase = true)
    var selectedPropertyType by remember { mutableStateOf("") }
    var propertyDescription by remember { mutableStateOf("") }
    val propertyTypes = listOf("Delicate", "Hard", "Documents", "Other")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Details Card
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = surfaceColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DetailItem(icon = Icons.Default.Person, label = "Driver", value = name, valueColor = colorScheme.onSurface, iconTint = orangeColor, iconBg = iconBgColor)
                DetailItem(icon = Icons.Default.LocationOn, label = "From", value = from, valueColor = colorScheme.onSurface, iconTint = orangeColor, iconBg = iconBgColor)
                DetailItem(icon = Icons.Default.LocationOn, label = "To", value = to, valueColor = colorScheme.onSurface, iconTint = orangeColor, iconBg = iconBgColor)
                DetailItem(icon = Icons.Default.Info, label = "Price", value = "UGX $price", valueColor = orangeColor, iconTint = orangeColor, iconBg = iconBgColor)
                
                if (isDelivery) {
                    HorizontalDivider(color = colorScheme.outlineVariant)
                    Text(
                        text = "Property Description",
                        fontWeight = FontWeight.Bold,
                        color = navyColor,
                        fontSize = 16.sp
                    )
                    
                    // Options for property type
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        propertyTypes.forEach { type ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedPropertyType = type }
                                    .padding(vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedPropertyType == type,
                                    onClick = { selectedPropertyType = type },
                                    colors = RadioButtonDefaults.colors(selectedColor = orangeColor)
                                )
                                Text(text = type, color = colorScheme.onSurface, fontSize = 14.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Text input for detailed description
                    OutlinedTextField(
                        value = propertyDescription,
                        onValueChange = { propertyDescription = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Write property details...", fontSize = 14.sp) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = orangeColor,
                            unfocusedBorderColor = colorScheme.outline,
                            focusedContainerColor = colorScheme.surface,
                            unfocusedContainerColor = colorScheme.surface
                        )
                    )
                }
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
            Icon(Icons.Default.Place, contentDescription = null, tint = colorScheme.onSecondary)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Track Ride", color = colorScheme.onSecondary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
            colors = ButtonDefaults.buttonColors(containerColor = orangeColor),
            enabled = !isDelivery || (selectedPropertyType.isNotEmpty() && propertyDescription.isNotBlank())
        ) {
            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = colorScheme.onPrimary)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Confirm Request", color = colorScheme.onPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
            border = BorderStroke(1.dp, colorScheme.outline),
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
            Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
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
