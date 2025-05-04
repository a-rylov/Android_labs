package com.rylov.tipseveryday

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rylov.tipseveryday.adapter.RecipeAdapter
import com.rylov.tipseveryday.model.Recipe
import com.rylov.tipseveryday.model.RecipeDataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recipesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recipes = RecipeDataSource.getRecipes()
        val adapter = RecipeAdapter(recipes) { recipe ->
            showRecipeDetails(recipe)
        }
        recyclerView.adapter = adapter
    }

    private fun showRecipeDetails(recipe: Recipe) {
        val intent = Intent(this, RecipeDetailActivity::class.java).apply {
            putExtra("recipe", recipe)
        }
        startActivity(intent)
    }
}