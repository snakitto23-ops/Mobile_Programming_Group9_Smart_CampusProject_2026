package com.example.mobile_programming_group9_smart_campusproject_2026.model

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class User(
    val name: String,
    val email: String,
    val role: String, // Student or Driver
    val phone: String,
    val imageUri: Uri?
)

// Global state for simplicity in this project
var currentUser by mutableStateOf<User?>(null)
