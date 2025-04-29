package com.rylov.mycity.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rylov.mycity.repository.CityRepository
import com.rylov.mycity.model.Recommendation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavController,
    categoryId: Int
) {
    val recommendations = CityRepository.getRecommendations(categoryId)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Рекомендации") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(recommendations) { recommendation ->
                RecommendationItem(recommendation = recommendation) {
                    navController.navigate("detail/${recommendation.id}")
                }
                Divider()
            }
        }
    }
}

@Composable
fun RecommendationItem(
    recommendation: Recommendation,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = recommendation.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = recommendation.address,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Рейтинг: ${"%.1f".format(recommendation.rating)}/5",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}