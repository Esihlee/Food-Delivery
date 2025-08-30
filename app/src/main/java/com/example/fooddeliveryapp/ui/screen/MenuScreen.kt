package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R

@Composable
fun MenuScreen() {
    val customFont = FontFamily(
        Font(R.font.luckiest_guy)
    )
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .background(Color(0xFFF4E0C0))
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF4E0C0))
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "UNI-EATS MENU",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFd36d0e),
                        fontFamily = customFont
                        )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ) {
                    item {
                        MenuSectionBoxes(
                            title = "Burgers",
                            items = listOf(
                                "Classic Beef Burger – R45",
                                "Chicken Fillet Burger – R40",
                                "Veggie Burger (Vegan) – R40",
                                "Cheese Burger – R50",
                                "(Add Fries for R15)"
                            ),
                            imageRes = R.drawable.burger
                        )
                    }

                    item {
                        MenuSectionBoxes(
                            title = "Tacos",
                            items = listOf(
                                "Beef Mince Tacos (3 pieces) – R55",
                                "Spicy Chicken Tacos (3 pieces) – R50",
                                "Bean & Veggie Tacos (Vegan, 3 pieces) – R50",
                                "(Served with salsa & sour cream)"
                            ),
                            imageRes = R.drawable.taco
                        )
                    }

                    item {
                        MenuSectionBoxes(
                            title = "Wraps",
                            items = listOf(
                                "Grilled Chicken Wrap – R55",
                                "Beef & Cheese Wrap – R60",
                                "Veggie & Hummus Wrap (Vegan) – R50",
                                "(Served with small fries)"
                            ),
                            imageRes = R.drawable.wraps
                        )
                    }

                    item {
                        MenuSectionBoxes(
                            title = "Pizza",
                            items = listOf(
                                "Margherita – R65",
                                "Pepperoni – R75",
                                "Chicken & Mushroom – R80",
                                "BBQ Beef – R85",
                                "Veggie Delight – R70"
                            ),
                            imageRes = R.drawable.pizza
                        )
                    }

                    item {
                        MenuSectionBoxes(
                            title = "Fries",
                            items = listOf(
                                "Small Fries – R20",
                                "Large Fries – R30",
                                "Cheesy Fries – R35",
                                "Chilli Fries – R35"
                            ),
                            imageRes = R.drawable.fries
                        )
                    }

                    item {
                        MenuSectionBoxes(
                            title = "Amagwinya",
                            items = listOf(
                                "Plain Amagwinya (1 piece) – R10",
                                "Amagwinya with Mince – R25",
                                "Amagwinya with Cheese & Polony – R20",
                                "Sweet Amagwinya (with jam) – R15"
                            ),
                            imageRes = R.drawable.amagwinya
                        )
                    }

                    item {
                        MenuSectionBoxes(
                            title = "Cool Drinks",
                            items = listOf(
                                "Bottled Water – R15",
                                "Soft Drinks (Coke, Fanta, Sprite) – R20",
                                "Iced Tea – R22",
                                "Fresh Juice (Orange, Apple) – R25",
                                "Energy Drink – R30"
                            ),
                            imageRes = R.drawable.cool_drink
                        )
                    }

                    item {
                        MenuSectionBoxes(
                            title = "Hot Drinks",
                            items = listOf(
                                "Coffee – R25",
                                "Cappuccino – R30",
                                "Hot Chocolate – R30",
                                "Rooibos Tea – R20"
                            ),
                            imageRes = R.drawable.hot_drink
                        )
                    }

                    item {
                        ComboSection()
                    }

                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            onClick = { /* TODO: Handle cart click */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFd36d0e))
                        ) {
                            Text("CART", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun MenuSectionBoxes(title: String, items: List<String>, imageRes: Int) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFFFF3E0))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            items.forEach { itemText ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(6.dp))
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                        .padding(bottom = 6.dp)
                ) {
                    Text(text = itemText, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun ComboSection() {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFFFF3E0))
                .padding(12.dp)

        ) {
            Text("Combos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text("• Burger + Fries + Soft Drink – R75")
            Text("• 3 Tacos + Juice – R70")
            Text("• Wrap + Small Fries + Drink – R80")
            Text("• Pizza + 2 Drinks – R120")
            Text("• 2 Amagwinya + Coffee – R35")
        }
    }
}
