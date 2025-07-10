package com.drron.sabora.ui.screen

object Screen {
    object RecipeList {
        const val route = "recipe_list"
    }
    object RecipeEditor {
        const val route = "recipe_editor"
    }
    object RecipeDetail {
        const val route = "recipe_detail/{recipeId}"
        fun createRoute(recipeId: Int) = "recipe_detail/$recipeId"
    }
}