package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.dao.UserDAO
import com.example.fooddeliveryapp.data.entity.User
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    navController: NavController,
    userDao: UserDAO,
    onSignupSuccess: (email: String, role: String) -> Unit

) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("Student") }
    var expanded by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val roles = listOf("Student", "Vendor")
    val black = Color(0xFF000000)
    val orange = Color(0xFFFF9800)

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedBorderColor = black,
        focusedBorderColor = black,
        cursorColor = orange,
        focusedLabelColor = black,
        unfocusedLabelColor = black,
        focusedTextColor = black,
        unfocusedTextColor = black
    )

    val customFont = FontFamily(Font(R.font.luckiest_guy))
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.student_home_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.7f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Sign Up UNI-EATS",
                fontFamily = customFont,
                fontSize = 55.sp,
                fontWeight = FontWeight.Bold,
                color = orange
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Role Dropdown (Student or Vendor)
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedRole,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Role") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor(),
                    colors = textFieldColors
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    roles.forEach { role ->
                        DropdownMenuItem(
                            text = { Text(text = role) },
                            onClick = {
                                selectedRole = role
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    errorMessage = null

                    if (name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                        errorMessage = "Please fill in all fields"
                        return@Button
                    }
                    if (password != confirmPassword) {
                        errorMessage = "Passwords do not match"
                        return@Button
                    }

                    coroutineScope.launch {
                        try {
                            val existingUser = userDao.getUserByEmail(email)
                            if (existingUser != null) {
                                errorMessage = "Email already registered"
                            } else {
                                val user = User(
                                    email = email,
                                    name = name,
                                    password = password,
                                    role = selectedRole
                                )
                                userDao.insertUser(user)

                                // Navigate back to login
                                navController.navigate("login") {
                                    popUpTo("signup") { inclusive = true }
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            errorMessage = "Error: ${e.localizedMessage}"
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orange),
                shape = RoundedCornerShape(20)
            ) {
                Text("SIGN UP", color = Color.White)
            }

            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = { navController.navigate("login") }) {
                Text("Already have an account? Login", color = orange)
            }
        }
    }
}
