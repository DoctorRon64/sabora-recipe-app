package com.drron.sabora.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.drron.sabora.data.Ingredient

@Composable
fun IngredientItem(
    ingredient: Ingredient,
    onUpdate: (Ingredient) -> Unit,
    onRemove: () -> Unit
) {
    var name by remember { mutableStateOf(ingredient.name) }
    var quantity by remember { mutableStateOf(ingredient.quantity.toString()) }
    var selectedUnit by remember { mutableStateOf(ingredient.unit) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = name,
            onValueChange = {
                name = it
                onUpdate(ingredient.copy(name = it))
            },
            label = { Text("Ingredient") },
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        TextField(
            value = quantity,
            onValueChange = {
                quantity = it
                it.toFloatOrNull()?.let { q ->
                    onUpdate(ingredient.copy(quantity = q))
                }
            },
            label = { Text("Qty") },
            modifier = Modifier.width(80.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        DropdownMenuBox(
            options = com.drron.sabora.data.Unit.entries,
            selected = selectedUnit,
            onSelect = {
                selectedUnit = it
                onUpdate(ingredient.copy(unit = it))
            }
        )

        IconButton(onClick = onRemove) {
            Icon(Icons.Default.Delete, contentDescription = "Remove Ingredient")
        }
    }
}

@Composable
fun <T> DropdownMenuBox(
    options: List<T>,
    selected: T,
    onSelect: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(onClick = { expanded = true }) {
            Text(selected.toString())
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach {
                DropdownMenuItem(onClick = {
                    onSelect(it)
                    expanded = false
                }, text = { Text(it.toString()) })
            }
        }
    }
}
