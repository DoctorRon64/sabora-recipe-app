package com.drron.sabora.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.drron.sabora.data.Ingredient
import com.drron.sabora.data.Recipe
import com.drron.sabora.data.Unit
import com.drron.sabora.ui.component.*
import com.drron.sabora.viewmodel.RecipeViewModel

@Composable
fun RecipeEditorScreen(navController: NavController, vm: RecipeViewModel = viewModel()) {
    var recipe by remember { mutableStateOf(Recipe()) }
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 80.dp)) {

        ImagePicker(recipe.imageUri) {
            recipe = recipe.copy(imageUri = it.toString())
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = recipe.title,
            onValueChange = { recipe = recipe.copy(title = it) },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Ingredients", style = MaterialTheme.typography.titleMedium)
        recipe.ingredients.forEachIndexed { i, ing ->
            IngredientItem(
                ingredient = ing,
                onUpdate = {
                    val updated = recipe.ingredients.toMutableList()
                    updated[i] = it
                    recipe = recipe.copy(ingredients = updated)
                },
                onRemove = {
                    val updated = recipe.ingredients.toMutableList()
                    updated.removeAt(i)
                    recipe = recipe.copy(ingredients = updated)
                }
            )
        }

        Button(onClick = {
            recipe = recipe.copy(ingredients = (recipe.ingredients + Ingredient("", 0f, Unit.G)).toMutableList())
        }) {
            Text("Add Ingredient")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Steps", style = MaterialTheme.typography.titleMedium)
        recipe.steps.forEachIndexed { i, step ->
            StepItem(
                step = step,
                index = i,
                onUpdate = {
                    val updated = recipe.steps.toMutableList()
                    updated[i] = it
                    recipe = recipe.copy(steps = updated)
                },
                onRemove = {
                    val updated = recipe.steps.toMutableList()
                    updated.removeAt(i)
                    recipe = recipe.copy(steps = updated)
                }
            )
        }

        Button(onClick = {
            recipe = recipe.copy(steps = recipe.steps + "")
        }) {
            Text("Add Step")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            vm.addRecipe(recipe)
            navController.popBackStack()
        }) {
            Text("Save Recipe")
        }
    }
}
