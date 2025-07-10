package com.drron.sabora.ui.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.drron.sabora.viewmodel.RecipeViewModel
import com.drron.sabora.viewmodel.RecipeViewModelFactory

// In your composable
@Composable
fun RecipeListScreen(navController: NavController) {
    val context = LocalContext.current
    val vm: RecipeViewModel = viewModel(factory = RecipeViewModelFactory(context))

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            "Your Recipes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        vm.recipes.forEach { recipe ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .clickable {
                        navController.navigate(Screen.RecipeDetail.createRoute(recipe.id))
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(recipe.imageUri),
                        contentDescription = "Recipe Image",
                        modifier = Modifier.size(64.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = recipe.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .alignByBaseline()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate(Screen.RecipeEditor.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create New Recipe")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
