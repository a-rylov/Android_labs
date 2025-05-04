package com.rylov.mycity.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rylov.mycity.ui.screens.CategoryScreen
import com.rylov.mycity.ui.screens.DetailScreen
import com.rylov.mycity.ui.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("category/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: 0
            CategoryScreen(
                navController = navController,
                categoryId = categoryId
            )
        }
        composable("detail/{recommendationId}") { backStackEntry ->
            val recommendationId = backStackEntry.arguments?.getString("recommendationId")?.toIntOrNull() ?: 0
            DetailScreen(
                navController = navController,
                recommendationId = recommendationId
            )
        }
    }
}