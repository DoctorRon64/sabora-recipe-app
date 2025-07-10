package com.drron.sabora.ui.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImagePicker(
    imageUri: String?,
    onImageSelected: (Uri) -> Unit
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(GetContent()) { uri: Uri? ->
        uri?.let { onImageSelected(it) }
    }

    Box(modifier = Modifier.clickable { launcher.launch("image/*") }) {
        if (imageUri != null) {
            AsyncImage(
                model = imageUri,
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .height(200.dp)
            )
        } else {
            Text("Click to select an image")
        }
    }
}
