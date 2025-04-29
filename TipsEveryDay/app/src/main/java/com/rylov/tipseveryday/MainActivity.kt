package com.rylov.tipseveryday

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rylov.tipseveryday.adapter.RecipeAdapter
import com.rylov.tipseveryday.model.Recipe

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recipesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recipes = createRecipesList()
        val adapter = RecipeAdapter(recipes) { recipe ->
            showRecipeDetails(recipe)
        }
        recyclerView.adapter = adapter
    }

    private fun createRecipesList(): List<Recipe> {
        return listOf(
            Recipe(
                id = 1,
                day = 1,
                title = "Картофельные драники",
                shortDescription = "Очень простой и быстрый рецепт картофельных драников",
                fullDescription = "1. Картофель очищаем и натираем на крупной терке, сразу выкладываем на сито или дуршлаг. Отжимаем\n" +
                        "2. Добавляем яйца, соль, перец. Перемешиваем\n" +
                        "3. Жарим картофельные драники на среднем огне в достаточном количестве растительного масла до румяности с обеих сторон.\n" +
                        "Приятного аппетита!",
                imageId = R.drawable.draniki
            ),
            Recipe(
                id = 2,
                day = 2,
                title = "Ленивый хачапури на сковороде",
                shortDescription = "Очень вкусная и мягкая лепёшка с сыром на сковороде.",
                fullDescription = "1. В миске смешать яйцо, соль, сахар, молоко, муку и разрыхлитель. Перемешать венчиком. Туда же натереть на средней тёрке сыр сулугуни\n" +
                        "2. Сковороду хорошо разогреть, смазать тонким слоем растительного масла. Вылить в сковороду тесто.\n" +
                        "3. Жарить сырную лепёшку под закрытой крышкой на маленьком огне 7-8 минут, пока тесто сверху не схватится.\n" +
                        "4. Аккуратно перевернуть лепёшку и жарить с другой стороны 2-3 минуты, до румяности.\n" +
                        "Приятного аппетита!",
                imageId = R.drawable.hachapuri
            ),
            Recipe(
                id = 3,
                day = 3,
                title = "Шоколадные маффины в чашках (в микроволновке)",
                shortDescription = "Шоколадные кексы легко и просто выпечь в микроволновке, прямо в чашках, в которых их потом удобно и подавать.",
                fullDescription = "1. Муку и разрыхлитель просейте в миску. Всыпьте сахар, ванилин и порошок какао. Венчиком перемешайте.\n" +
                        "2. Влейте яйцо, молоко и растительное масло. Венчиком вымешайте однородное шоколадное тесто.\n" +
                        "3. Разложите тесто по чашкам, заполняя каждую из них не больше, чем наполовину\n" +
                        "4. Готовьте кексы в микроволновой печи при мощности 800 Вт в течение 3 минут. Достав, посыпьте сахарной пудрой.\n" +
                        "Приятного аппетита!",
                imageId = R.drawable.muffins
            ),
            Recipe(
                id = 4,
                day = 4,
                title = "Рулеты из лаваша с крабовыми палочками",
                shortDescription = "Закуска из лаваша с начинкой",
                fullDescription = "1. Натираем на тёрке крабовые палочки, огурцы, вареные яйца. К ним добавляем выдавленный чеснок и мелко нарезанную зелень." +
                        "Добавляем майонез, соль и тщательно перемешиваем начинку.\n" +
                        "2. Делим лаваш на 2 части. Размазываем приготовленную начинку по одному листу равномерным слоем, накрываем вторым листом и повторяем процедуру.\n" +
                        "3. Далее сворачиваем лаваш с начинкой в рулет и отправляем в холодильник примерно на 30 минут.\n" +
                        "4. После того, как рулет из лаваша с крабовыми палочками охладился и пропитался, нарезаем его на кусочки.\n" +
                        "Приятного аппетита!",
                imageId = R.drawable.rouletes
            ),
            Recipe(
                id = 5,
                day = 5,
                title = "Пирожное \"Картошка\"",
                shortDescription = "Пирожные к чаю",
                fullDescription = "1. Печенье раздавить скалкой в крошку (или измельчить блендером). Подготовленное печенье выложить в миску.\n" +
                        "2. К печенью добавить сгущенное молоко и мягкое сливочное масло. Далее добавить какао.\n" +
                        "3. Все тщательно перемешать ложкой, а затем вымесить руками.\n" +
                        "4. Сформировать пирожные руками в виде картошек (или шариков). Обсыпать грецкими орехами. Поставить в холодильник.\n" +
                        "Приятного аппетита!",
                imageId = R.drawable.kartoshka
            )
        )
    }

    private fun showRecipeDetails(recipe: Recipe) {
        val intent = Intent(this, RecipeDetailActivity::class.java).apply {
            putExtra("recipe", recipe)
        }
        startActivity(intent)
    }
}