package com.drron.sabora.data

data class Recipe(
    val id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var imageUri: String = "",
    var ingredients: MutableList<Ingredient> = mutableListOf(),
    var steps: MutableList<String> = mutableListOf()
)

data class Ingredient(
    val name: String,
    val quantity: Float,
    val unit: Unit
)

enum class Unit {
    G, KG, ML, L, TBSP, TSP
}
