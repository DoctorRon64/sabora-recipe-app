package com.drron.sabora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drron.sabora.ui.screen.RecipeDetailScreen
import com.drron.sabora.ui.screen.RecipeEditorScreen
import com.drron.sabora.ui.screen.Screen
import com.drron.sabora.ui.theme.SaboraTheme
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.drron.sabora.ui.screen.RecipeListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaboraTheme {
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.RecipeList.route
                    ) {
                        composable(Screen.RecipeList.route) {
                            RecipeListScreen(navController)
                        }
                        composable(Screen.RecipeEditor.route) {
                            RecipeEditorScreen(navController)
                        }
                        composable(
                            route = Screen.RecipeDetail.route,
                            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
                            RecipeDetailScreen(navController, recipeId)
                        }
                    }
                }
            }
        }
    }
}