package com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackingScreen(navController: NavController) {
    val orangeColor = Color(0xFFFF9800)
    val navyColor = Color(0xFF0D3B66)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Live Tracking", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = navyColor)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF0F0F0))
        ) {
            // Simulated Map Area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(24.dp))
            ) {
                SimulatedMap()
            }

            // Ride Info Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFFFFF3E0),
                            modifier = Modifier.size(50.dp)
                        ) {
                            Icon(
                                Icons.Default.Navigation,
                                contentDescription = null,
                                tint = orangeColor,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Driver: Kibogina", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text("Boda Boda • UX 452C", color = Color.Gray)
                        }
                    }
                    
                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Red, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("ETA: 4 mins away", fontWeight = FontWeight.Medium)
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(
                        onClick = { /* Call Driver */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = orangeColor),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Call Driver")
                    }
                }
            }
        }
    }
}

@Composable
fun SimulatedMap() {
    val infiniteTransition = rememberInfiniteTransition(label = "mapAnimation")
    
    // Animate progress from 0 to 1
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rideProgress"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        // Define the road path (from Bombo to Kampala Campus)
        val roadPath = Path().apply {
            moveTo(w * 0.2f, h * 0.8f) // Bombo (Start)
            quadraticTo(w * 0.5f, h * 0.7f, w * 0.6f, h * 0.4f)
            quadraticTo(w * 0.7f, h * 0.2f, w * 0.8f, h * 0.2f) // Kampala Campus (End)
        }

        // Draw the Road
        drawPath(
            path = roadPath,
            color = Color.LightGray,
            style = Stroke(width = 20f)
        )
        
        // Draw dashed line in the middle of the road
        drawPath(
            path = roadPath,
            color = Color.White,
            style = Stroke(width = 2f)
        )

        // PathMeasure to find the position on the path
        val pathMeasure = PathMeasure()
        pathMeasure.setPath(roadPath, false)
        
        val pos = pathMeasure.getPosition(pathMeasure.length * progress)

        // Draw Start Point (Bombo)
        drawCircle(
            color = Color(0xFF4CAF50),
            radius = 15f,
            center = Offset(w * 0.2f, h * 0.8f)
        )

        // Draw End Point (Kampala Campus)
        drawCircle(
            color = Color(0xFFF44336),
            radius = 15f,
            center = Offset(w * 0.8f, h * 0.2f)
        )

        // Draw the Ride (Moving Car/Boda)
        drawCircle(
            color = Color(0xFF0D3B66),
            radius = 20f,
            center = pos
        )
        
        // Draw a smaller circle inside for styling
        drawCircle(
            color = Color(0xFFFF9800),
            radius = 10f,
            center = pos
        )
    }
}
