package com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CallScreen(navController: NavController, name: String, phone: String) {
    val colorScheme = MaterialTheme.colorScheme
    val navyColor = colorScheme.secondary
    val orangeColor = colorScheme.primary

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(navyColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // Driver Image Placeholder
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(colorScheme.onSecondary.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = colorScheme.onSecondary
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = name,
            color = colorScheme.onSecondary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = phone,
            color = colorScheme.onSecondary.copy(alpha = 0.7f),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Calling...",
            color = orangeColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.weight(1f))

        // Call Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /* Mute logic */ },
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.onSecondary.copy(alpha = 0.1f), CircleShape)
            ) {
                Icon(Icons.Default.Mic, contentDescription = "Mute", tint = colorScheme.onSecondary)
            }

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Red, CircleShape)
            ) {
                Icon(
                    Icons.Default.CallEnd,
                    contentDescription = "End Call",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            IconButton(
                onClick = { /* Speaker logic */ },
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.onSecondary.copy(alpha = 0.1f), CircleShape)
            ) {
                Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Speaker", tint = colorScheme.onSecondary)
            }
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}
