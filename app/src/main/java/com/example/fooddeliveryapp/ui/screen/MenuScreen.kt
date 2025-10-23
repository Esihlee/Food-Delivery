package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    val customFont = FontFamily(Font(R.font.luckiest_guy))

    // Selected items per section
    val selectedItems = remember { mutableStateMapOf<String, MutableSet<String>>() }

    // Total price state
    var totalPrice by remember { mutableStateOf(0) }

    // Map of item prices (extract from menu text)
    val itemPrices = mapOf(
        // Burgers
        "Classic Beef Burger – R45" to 45,
        "Chicken Fillet Burger – R40" to 40,
        "Veggie Burger (Vegan) – R40" to 40,
        "Cheese Burger – R50" to 50,
        "(Add Fries for R15)" to 15,
        // Tacos
        "Beef Mince Tacos (3 pieces) – R55" to 55,
        "Spicy Chicken Tacos (3 pieces) – R50" to 50,
        "Bean & Veggie Tacos (Vegan, 3 pieces) – R50" to 50,
        "(Served with salsa & sour cream)" to 0,
        // Wraps
        "Grilled Chicken Wrap – R55" to 55,
        "Beef & Cheese Wrap – R60" to 60,
        "Veggie & Hummus Wrap (Vegan) – R50" to 50,
        "(Served with small fries)" to 0,
        // Pizza
        "Margherita – R65" to 65,
        "Pepperoni – R75" to 75,
        "Chicken & Mushroom – R80" to 80,
        "BBQ Beef – R85" to 85,
        "Veggie Delight – R70" to 70,
        // Fries
        "Small Fries – R20" to 20,
        "Large Fries – R30" to 30,
        "Cheesy Fries – R35" to 35,
        "Chilli Fries – R35" to 35,
        // Amagwinya
        "Plain Amagwinya (1 piece) – R10" to 10,
        "Amagwinya with Mince – R25" to 25,
        "Amagwinya with Cheese & Polony – R20" to 20,
        "Sweet Amagwinya (with jam) – R15" to 15,
        // Cool Drinks
        "Bottled Water – R15" to 15,
        "Soft Drinks (Coke, Fanta, Sprite) – R20" to 20,
        "Iced Tea – R22" to 22,
        "Fresh Juice (Orange, Apple) – R25" to 25,
        "Energy Drink – R30" to 30,
        // Hot Drinks
        "Coffee – R25" to 25,
        "Cappuccino – R30" to 30,
        "Hot Chocolate – R30" to 30,
        "Rooibos Tea – R20" to 20,
        // Combos
        "Burger + Fries + Soft Drink – R75" to 75,
        "3 Tacos + Juice – R70" to 70,
        "Wrap + Small Fries + Drink – R80" to 80,
        "Pizza + 2 Drinks – R120" to 120,
        "2 Amagwinya + Coffee – R35" to 35
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
                    // Function to add sections
                    fun addMenuSection(title: String, items: List<String>, imageRes: Int) {
                        item {
                            MenuSectionBoxes(
                                title = title,
                                items = items,
                                imageRes = imageRes,
                                selectedItems = selectedItems,
                                itemPrices = itemPrices,
                                onSelectionChange = { item, isSelected ->
                                    if (isSelected) totalPrice += itemPrices[item] ?: 0
                                    else totalPrice -= itemPrices[item] ?: 0
                                }
                            )
                        }
                    }

                    addMenuSection(
                        "Burgers",
                        listOf(
                            "Classic Beef Burger – R45",
                            "Chicken Fillet Burger – R40",
                            "Veggie Burger (Vegan) – R40",
                            "Cheese Burger – R50",
                            "(Add Fries for R15)"
                        ),
                        R.drawable.burger
                    )

                    addMenuSection(
                        "Tacos",
                        listOf(
                            "Beef Mince Tacos (3 pieces) – R55",
                            "Spicy Chicken Tacos (3 pieces) – R50",
                            "Bean & Veggie Tacos (Vegan, 3 pieces) – R50",
                            "(Served with salsa & sour cream)"
                        ),
                        R.drawable.taco
                    )

                    addMenuSection(
                        "Wraps",
                        listOf(
                            "Grilled Chicken Wrap – R55",
                            "Beef & Cheese Wrap – R60",
                            "Veggie & Hummus Wrap (Vegan) – R50",
                            "(Served with small fries)"
                        ),
                        R.drawable.wraps
                    )

                    addMenuSection(
                        "Pizza",
                        listOf(
                            "Margherita – R65",
                            "Pepperoni – R75",
                            "Chicken & Mushroom – R80",
                            "BBQ Beef – R85",
                            "Veggie Delight – R70"
                        ),
                        R.drawable.pizza
                    )

                    addMenuSection(
                        "Fries",
                        listOf(
                            "Small Fries – R20",
                            "Large Fries – R30",
                            "Cheesy Fries – R35",
                            "Chilli Fries – R35"
                        ),
                        R.drawable.fries
                    )

                    addMenuSection(
                        "Amagwinya",
                        listOf(
                            "Plain Amagwinya (1 piece) – R10",
                            "Amagwinya with Mince – R25",
                            "Amagwinya with Cheese & Polony – R20",
                            "Sweet Amagwinya (with jam) – R15"
                        ),
                        R.drawable.amagwinya
                    )

                    addMenuSection(
                        "Cool Drinks",
                        listOf(
                            "Bottled Water – R15",
                            "Soft Drinks (Coke, Fanta, Sprite) – R20",
                            "Iced Tea – R22",
                            "Fresh Juice (Orange, Apple) – R25",
                            "Energy Drink – R30"
                        ),
                        R.drawable.cool_drink
                    )

                    addMenuSection(
                        "Hot Drinks",
                        listOf(
                            "Coffee – R25",
                            "Cappuccino – R30",
                            "Hot Chocolate – R30",
                            "Rooibos Tea – R20"
                        ),
                        R.drawable.hot_drink
                    )

                    item {
                        ComboSection(
                            selectedItems = selectedItems,
                            itemPrices = itemPrices,
                            onSelectionChange = { item, isSelected ->
                                if (isSelected) totalPrice += itemPrices[item] ?: 0
                                else totalPrice -= itemPrices[item] ?: 0
                            }
                        )
                    }

                    // Display total price
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "Total: R$totalPrice",
                            modifier = Modifier.padding(horizontal = 12.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // CART button
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            onClick = {
                                println("Selected items: $selectedItems")
                                println("Total price: R$totalPrice")
                            },
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
fun MenuSectionBoxes(
    title: String,
    items: List<String>,
    imageRes: Int,
    selectedItems: MutableMap<String, MutableSet<String>>,
    itemPrices: Map<String, Int>,
    onSelectionChange: (item: String, isSelected: Boolean) -> Unit
) {
    val sectionSelectedItems = selectedItems.getOrPut(title) { mutableSetOf() }

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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(6.dp))
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                        .padding(bottom = 6.dp)
                ) {
                    val checked = sectionSelectedItems.contains(itemText)
                    Checkbox(
                        checked = checked,
                        onCheckedChange = {
                            if (it) sectionSelectedItems.add(itemText)
                            else sectionSelectedItems.remove(itemText)
                            onSelectionChange(itemText, it)
                        },
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFFd36d0e))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = itemText, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun ComboSection(
    selectedItems: MutableMap<String, MutableSet<String>>,
    itemPrices: Map<String, Int>,
    onSelectionChange: (item: String, isSelected: Boolean) -> Unit
) {
    val sectionSelectedItems = selectedItems.getOrPut("Combos") { mutableSetOf() }

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
            val comboItems = listOf(
                "Burger + Fries + Soft Drink – R75",
                "3 Tacos + Juice – R70",
                "Wrap + Small Fries + Drink – R80",
                "Pizza + 2 Drinks – R120",
                "2 Amagwinya + Coffee – R35"
            )

            comboItems.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(6.dp))
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                        .padding(bottom = 6.dp)
                ) {
                    val checked = sectionSelectedItems.contains(item)
                    Checkbox(
                        checked = checked,
                        onCheckedChange = {
                            if (it) sectionSelectedItems.add(item)
                            else sectionSelectedItems.remove(item)
                            onSelectionChange(item, it)
                        },
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFFd36d0e))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(item)
                }
            }
        }
    }
}
