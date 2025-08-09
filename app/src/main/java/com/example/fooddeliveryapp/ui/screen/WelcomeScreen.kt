package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.fooddeliveryapp.R

@Composable
fun WelcomeScreen(navToLogin: () -> Unit, navToSignup: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = "Welcome Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            val customFont = FontFamily(
                Font(R.font.luckiest_guy)
            )

            Text(
                text = "WELCOME TO UNI-EATS",
                fontSize = 55.sp,
                fontFamily = customFont,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )

            Spacer(modifier = Modifier.weight(1f))

            // 🟢 Buttons at the bottom
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = navToLogin,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0x88FF9800)), // 👈 same color
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text("LOGIN",
                        color = Color.White,
                        fontFamily = customFont,
                        fontWeight = FontWeight.Bold
                    )

                }

                Button(
                    onClick = navToSignup,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0x88FF9800)), // 👈 same color
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text("SIGNUP",
                        color = Color.White,
                        fontFamily = customFont,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "©UNI-EATS. All Rights Reserved",
                fontSize = 12.sp,
                color = Color.LightGray
            )

        }
    }
}
