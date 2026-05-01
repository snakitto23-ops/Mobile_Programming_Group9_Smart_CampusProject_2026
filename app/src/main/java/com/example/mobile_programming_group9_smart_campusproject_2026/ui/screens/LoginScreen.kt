package com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var otpCode by remember { mutableStateOf("") }
    var isOtpSent by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val orangeColor = Color(0xFFFF9800)
    val navyColor = Color(0xFF0D3B66)
    val backgroundColor = Color(0xFFF5F5F5)

    LaunchedEffect(Unit) {
        visible = true
    }

    // Side-by-side animation for the car
    val infiniteTransition = rememberInfiniteTransition(label = "carAnimation")
    val offsetX by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "carOffset"
    )

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(orangeColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "safety at low cost",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(initialOffsetY = { -100 })
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Car moves side-by-side using the animated offsetX
                    CarLogo(Modifier.size(100.dp).offset(x = offsetX.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Smart Campus Ride",
                        style = MaterialTheme.typography.headlineMedium,
                        color = navyColor,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            AnimatedContent(
                targetState = isOtpSent,
                transitionSpec = {
                    if (targetState) {
                        slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut()
                    } else {
                        slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut()
                    }
                }, label = ""
            ) { otpSent ->
                if (!otpSent) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text("University Email", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color.DarkGray) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = orangeColor,
                                unfocusedBorderColor = Color.LightGray,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = { Text("Password", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.DarkGray) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = orangeColor,
                                unfocusedBorderColor = Color.LightGray,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        Button(
                            onClick = { 
                                if (email.isNotEmpty() && password.isNotEmpty()) {
                                    isLoading = true
                                    isOtpSent = true
                                    isLoading = false
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = orangeColor),
                            enabled = !isLoading
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                            } else {
                                Text("Login", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
                            }
                        }
                    }
                } else {
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Verify Email", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = navyColor)
                            Text("Enter the 4-digit code sent to \n$email", textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                            
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            OutlinedTextField(
                                value = otpCode,
                                onValueChange = { if (it.length <= 4) otpCode = it },
                                modifier = Modifier.width(180.dp),
                                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 28.sp, fontWeight = FontWeight.Bold, letterSpacing = 8.sp),
                                shape = RoundedCornerShape(12.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = orangeColor,
                                    unfocusedBorderColor = Color.LightGray
                                )
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            Button(
                                onClick = { 
                                    if (otpCode.length == 4) {
                                        navController.navigate("home")
                                    }
                                },
                                modifier = Modifier.fillMaxWidth().height(50.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = orangeColor),
                                enabled = otpCode.length == 4
                            ) {
                                Text("VERIFY & LOGIN", fontWeight = FontWeight.Bold)
                            }

                            TextButton(onClick = { isOtpSent = false }) {
                                Text("Edit Email", color = orangeColor)
                            }
                        }
                    }
                }
            }

            if (!isOtpSent) {
                Spacer(modifier = Modifier.height(24.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("New student? ", color = Color.Gray)
                    TextButton(
                        onClick = { navController.navigate("register") },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("Register here", color = orangeColor, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun CarLogo(modifier: Modifier = Modifier) {
    val orangeColor = Color(0xFFFF9800)
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Body base with rounded corners
        drawRoundRect(
            color = orangeColor,
            topLeft = Offset(w * 0.05f, h * 0.45f),
            size = Size(w * 0.9f, h * 0.35f),
            cornerRadius = CornerRadius(12f, 12f)
        )
        
        // Cabin
        val cabinPath = Path().apply {
            moveTo(w * 0.25f, h * 0.45f)
            lineTo(w * 0.75f, h * 0.45f)
            lineTo(w * 0.68f, h * 0.25f)
            lineTo(w * 0.32f, h * 0.25f)
            close()
        }
        drawPath(cabinPath, orangeColor)

        // Headlights (White circles like in screenshot)
        drawCircle(
            color = Color.White,
            radius = w * 0.06f,
            center = Offset(w * 0.25f, h * 0.58f)
        )
        drawCircle(
            color = Color.White,
            radius = w * 0.06f,
            center = Offset(w * 0.75f, h * 0.58f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}
