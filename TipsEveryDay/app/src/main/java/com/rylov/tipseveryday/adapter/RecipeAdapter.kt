package com.rylov.tipseveryday.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rylov.tipseveryday.R
import com.rylov.tipseveryday.model.Recipe

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.recipeImageView)
        private val shortDescTextView: TextView =
            itemView.findViewById(R.id.shortDescriptionTextView)
        private val detailsButton: Button = itemView.findViewById(R.id.detailsButton)

        fun bind(recipe: Recipe) {
            dayTextView.text = "День ${recipe.day}"
            titleTextView.text = recipe.title
            imageView.setImageResource(recipe.imageId)
            shortDescTextView.text = recipe.shortDescription

            detailsButton.setOnClickListener {
                onItemClick(recipe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size
}