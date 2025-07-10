package com.drron.sabora.util

import com.drron.sabora.data.Recipe
import kotlinx.serialization.json.Json

object JsonExporter {
    fun toJson(recipes: List<Recipe>): String {
        val json = Json { prettyPrint = true }
        return json.encodeToString(recipes)
    }

    fun fromJson(jsonString: String): List<Recipe> {
        val json = Json { prettyPrint = true }
        return json.decodeFromString(jsonString)
    }
}