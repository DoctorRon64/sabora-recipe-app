package com.drron.sabora.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.drron.sabora.data.Ingredient
import com.drron.sabora.data.Recipe
import com.drron.sabora.util.JsonExporter
import androidx.core.content.edit

class RecipeViewModel(private val context: Context) : ViewModel() {
    private val prefs = context.getSharedPreferences("recipes_prefs", Context.MODE_PRIVATE)
    private val _recipes = mutableStateListOf<Recipe>()
    val recipes: List<Recipe> get() = _recipes

    private var nextId = 1

    init {
        loadRecipesFromJson()
    }

    fun loadRecipesFromJson() {
        val storedJson = prefs.getString("recipes_json", "[]") ?: "[]"
        val loaded = try {
            JsonExporter.fromJson(storedJson)
        } catch (e: Exception) {
            emptyList<Recipe>()
        }
        _recipes.clear()
        _recipes.addAll(loaded)
        nextId = (_recipes.maxOfOrNull { it.id } ?: 0) + 1
    }

    fun saveRecipesToJson() {
        val json = JsonExporter.toJson(_recipes)
        prefs.edit { putString("recipes_json", json) }
    }

    fun addRecipe(title: String, ingredients: List<Ingredient>, steps: List<String>) {
        val newRecipe = Recipe(
            id = nextId++,
            title = title,
            ingredients = ingredients.toMutableList(),
            steps = steps
        )
        _recipes.add(newRecipe)
        saveRecipesToJson()
    }

    fun addRecipe(recipe: Recipe) {
        val newRecipe = recipe.copy(id = nextId++)
        _recipes.add(newRecipe)
        saveRecipesToJson()
    }

    fun updateRecipe(updated: Recipe) {
        val index = _recipes.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            _recipes[index] = updated
            saveRecipesToJson()
        }
    }

    fun deleteRecipe(id: Int) {
        _recipes.removeAll { it.id == id }
        saveRecipesToJson()
    }

    fun exportRecipesToJson(): String {
        return JsonExporter.toJson(_recipes)
    }
}