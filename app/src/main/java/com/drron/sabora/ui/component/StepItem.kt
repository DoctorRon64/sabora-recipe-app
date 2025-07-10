package com.drron.sabora.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StepItem(
    step: String,
    index: Int,
    onUpdate: (String) -> Unit,
    onRemove: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        TextField(
            value = step,
            onValueChange = { onUpdate(it) },
            label = { Text("Step ${index + 1}") },
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = onRemove) {
            Icon(Icons.Default.Delete, contentDescription = "Remove Step")
        }
    }
}
