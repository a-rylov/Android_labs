package com.rylov.tipseveryday

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rylov.tipseveryday.model.Recipe

class RecipeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recipe = intent.getParcelableExtra<Recipe>("recipe")

        recipe?.let {
            findViewById<TextView>(R.id.detailDayTextView).text = "День ${recipe.day}"
            findViewById<TextView>(R.id.detailTitleTextView).text = it.title
            findViewById<ImageView>(R.id.detailImageView).setImageResource(it.imageId)
            findViewById<TextView>(R.id.detailDescriptionTextView).text = it.fullDescription
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}