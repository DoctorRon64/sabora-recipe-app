package com.drron.sabora.viewmodel

class RecipeViewModel : ViewModel() {
    private val _recipes = mutableStateListOf<Recipe>()
    val recipes: List<Recipe> get() = _recipes

    var currentRecipe = mutableStateOf(Recipe())
        private set

    fun addRecipe(recipe: Recipe) {
        _recipes.add(recipe)
    }

    fun updateRecipe(updated: Recipe) {
        val index = _recipes.indexOfFirst { it.id == updated.id }
        if (index != -1) _recipes[index] = updated
    }

    fun deleteRecipe(id: String) {
        _recipes.removeAll { it.id == id }
    }

    fun exportRecipesToJson(): String {
        return JsonExporter.toJson(_recipes)
    }
}