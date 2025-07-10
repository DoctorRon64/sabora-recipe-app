package com.drron.sabora.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.drron.sabora.viewmodel.RecipeViewModel

@Composable
fun RecipeDetailScreen(navController: NavController, recipeId: Int, vm: RecipeViewModel = viewModel()) {
    val recipe = vm.recipes.find { it.id == recipeId }

    if (recipe != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(recipe.title, style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Ingredients:")
            recipe.ingredients.forEach {
                Text("- ${it.quantity} ${it.unit} ${it.name}")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Steps:")
            recipe.steps.forEachIndexed { index, step ->
                Text("${index + 1}. $step")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
        }
    } else {
        Text("Recipe not found")
    }
}