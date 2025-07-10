package com.drron.sabora.data

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Int = 0,
    var title: String = "",
    var imageUri: String = "",
    var ingredients: MutableList<Ingredient> = mutableListOf(),
    var steps: List<String> = mutableListOf()
)

@Serializable
data class Ingredient(
    val name: String,
    val quantity: Float,
    val unit: Unit
)

enum class Unit {
    G, KG, ML, L, TBSP, TSP
}
