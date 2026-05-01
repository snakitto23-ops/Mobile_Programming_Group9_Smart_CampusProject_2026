package com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TrackingScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tracking Ride...", style = MaterialTheme.typography.headlineSmall)
        Text("Driver is 5 minutes away")
        
        Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(top = 32.dp)) {
            Text("Cancel / Back")
        }
    }
}
