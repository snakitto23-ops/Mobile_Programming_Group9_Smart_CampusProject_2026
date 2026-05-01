package com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Route
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
import com.example.mobile_programming_group9_smart_campusproject_2026.model.currentUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {
    val colorScheme = MaterialTheme.colorScheme
    val orangeColor = colorScheme.primary
    val backgroundColor = colorScheme.background

    val ridesTaken = currentUser?.rideHistory?.count { it.status == "Completed" } ?: 0
    val loginAttempts = currentUser?.loginAttempts ?: 0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notifications & Insights", color = colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = orangeColor)
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "System Notifications",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onBackground
            )

            NotificationItem(
                icon = Icons.Default.Route,
                title = "Ride Activity",
                description = "You have successfully completed $ridesTaken rides at Ndejje University.",
                color = orangeColor
            )

            NotificationItem(
                icon = Icons.AutoMirrored.Filled.Login,
                title = "Security Alert",
                description = "There have been $loginAttempts attempted logins to your account.",
                color = colorScheme.secondary
            )

            NotificationItem(
                icon = Icons.Default.Info,
                title = "App Update",
                description = "Smart Campus Ride is now updated with Dark Mode and History features.",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun NotificationItem(icon: ImageVector, title: String, description: String, color: Color) {
    val colorScheme = MaterialTheme.colorScheme
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(8.dp),
                color = color.copy(alpha = 0.1f)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    tint = color
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, color = colorScheme.onSurface)
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
