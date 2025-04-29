package com.rylov.artspace

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rylov.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtScreen()
            }
        }
    }
}

@Composable
fun ArtScreen() {
    val artworks = listOf(
        ArtPiece(R.drawable.art1, "Girl with a Pearl Earring", "Johannes Vermeer", "1665"),
        ArtPiece(R.drawable.art2, "The Arnolfini Portrait", "Jan van Eyck", "1434"),
        ArtPiece(R.drawable.art3, "Les Demoiselles d’Avignon", "Pablo Picasso", "1907")
    )

    var currentIndex by rememberSaveable { mutableIntStateOf(0) }
    val currentArt = artworks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtImage(imageId = currentArt.imageRes, title = currentArt.title)
        Spacer(modifier = Modifier.height(24.dp))
        ArtDescription(title = currentArt.title, artist = currentArt.artist, year = currentArt.year)
        Spacer(modifier = Modifier.height(48.dp))
        NavigationButtons(
            onPrevious = { currentIndex = getPreviousIndex(currentIndex, artworks.size) },
            onNext = { currentIndex = getNextIndex(currentIndex, artworks.size) }
        )
    }
}

@Composable
fun ArtImage(imageId: Int, title: String) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth(if (isLandscape) 0.5f else 1f)
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
                .aspectRatio(if (isLandscape) 1.5f else 1f)
        )
    }
}


@Composable
fun ArtDescription(title: String, artist: String, year: String) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth(if (isLandscape) 0.5f else 1f)
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFECEBF4)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = title, style = MaterialTheme.typography.headlineMedium)
            Text(text = "$artist, $year", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun NavigationButtons(onPrevious: () -> Unit, onNext: () -> Unit) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    Row(
        modifier = Modifier
            .fillMaxWidth(if (isLandscape) 0.5f else 1f)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPrevious,
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
        ) {
            Text("Previous")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            onClick = onNext,
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
        ) {
            Text("Next")
        }
    }
}

private fun getPreviousIndex(currentIndex: Int, size: Int): Int {
    return (currentIndex - 1 + size) % size
}

private fun getNextIndex(currentIndex: Int, size: Int): Int {
    return (currentIndex + 1) % size
}

data class ArtPiece(val imageRes: Int, val title: String, val artist: String, val year: String)

@Preview(showBackground = true)
@Composable
fun PreviewArtScreen() {
    ArtSpaceTheme {
        ArtScreen()
    }
}