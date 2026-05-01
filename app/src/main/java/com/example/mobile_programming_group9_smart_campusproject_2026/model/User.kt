package com.example.mobile_programming_group9_smart_campusproject_2026.model

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class RideHistoryItem(
    val driverName: String,
    val from: String,
    val to: String,
    val date: String,
    val price: String,
    val status: String // "Completed", "Cancelled"
)

data class User(
    val name: String,
    val email: String,
    val role: String, // Student or Driver
    val phone: String,
    val imageUri: Uri?,
    val rideHistory: List<RideHistoryItem> = emptyList(),
    var loginAttempts: Int = 0
)

// Global state for simplicity in this project
var currentUser by mutableStateOf<User?>(
    User(
        name = "Lomeling Peter",
        email = "lomeling.peter@stud.ndejjeuniversity.ac.ug",
        role = "Student",
        phone = "0755123456",
        imageUri = null,
        rideHistory = listOf(
            RideHistoryItem("Alex", "Main Campus", "Kampala Campus", "2026-04-28", "10,000", "Completed"),
            RideHistoryItem("Sarah", "Bombo", "Main Campus", "2026-04-25", "5,000", "Completed"),
            RideHistoryItem("Henry", "Luwero", "Kampala Campus", "2026-04-20", "10,000", "Cancelled")
        ),
        loginAttempts = 4
    )
)
