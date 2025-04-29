package com.rylov.mycity.repository

import com.rylov.mycity.R
import com.rylov.mycity.model.Category
import com.rylov.mycity.model.Recommendation

object CityRepository {
    private val categories = listOf(
        Category(1, "Кафе", R.drawable.ic_cafe),
        Category(2, "Рестораны", R.drawable.ic_restaurant),
        Category(3, "Парки", R.drawable.ic_park),
        Category(4, "Торговые центры", R.drawable.ic_mall)
    )

    private val recommendations = mapOf(
        1 to listOf(
            Recommendation(
                1,
                "Кафе быстрого питания \"ДакиЧо\"",
                "Быстро, вкусно, шаурма по-удмуртски.",
                R.drawable.cafe1,
                "ул. Удмуртская, 222Б",
                4.7f
            ),
            Recommendation(
                2,
                "Yoki",
                "Кафе паназиатской кухни.",
                R.drawable.cafe2,
                "ул. Холмогорова, 11",
                3.5f
            ),
            Recommendation(
                3,
                "Mama Pizza",
                "Сеть семейных кафе итальянской кухни.",
                R.drawable.cafe3,
                "ул. Майская, 9",
                4.9f
            ),
        ),
        2 to listOf(
            Recommendation(
                4,
                "Mono",
                "Ресторан с итальянским акцентом.",
                R.drawable.rest1,
                "ул. 10 лет Октября, 17а",
                4f
            ),
            Recommendation(
                5,
                "You and wine",
                "Авторская японская кухня. Винные дегустации и выездное обслуживание.",
                R.drawable.rest2,
                "ул. Пушкинская, 277",
                4.6f
            ),
            Recommendation(
                6,
                "Не Спица",
                "Вкусная еда и приятная атмосфера ждут вас!",
                R.drawable.rest3,
                "ул. Максима Горького, 76",
                4.8f
            ),
        ),
        3 to listOf(
            Recommendation(
                7,
                "Парк культуры и отдыха им. С.М. Кирова",
                "Отличное место для прогулок и занятий спортом.",
                R.drawable.park1,
                "ул. Кирова, 8а",
                4.7f
            ),
            Recommendation(
                8,
                "Парк им. М. Горького",
                "Шикарное место. Аттракционы на любой возраст, кафешки с разнообразным меню, красивая набережная с волшебной иллюминацией.",
                R.drawable.park2,
                "ул. Милиционная, 4",
                4.5f
            ),
            Recommendation(
                9,
                "Парк Космонавтов",
                "Парк культуры и отдыха «Парк Космонавтов» — это место, где можно провести время всей семьей.",
                R.drawable.park3,
                "ул. Воткинское ш., 118",
                4.4f
            ),
        ),
        4 to listOf(
            Recommendation(
                10,
                "Талисман",
                "ТЦ «Талисман» в Ижевске — это крупный торговый центр с разнообразными магазинами, кафе и развлечениями",
                R.drawable.mall1,
                "ул. Холмогорова, 11",
                4.8f
            ),
            Recommendation(
                11,
                "Эльгрин",
                "Приличный ТЦ, но разнообразие магазинов немного скудноватое, в основном это шоурумы, или маленькие магазинчики. Зато в офисной части наоборот, разнообразие огромное, от парикмахерских, до стоматологии.",
                R.drawable.mall2,
                "ул. 10 лет Октября, 53",
                4.5f
            ),
            Recommendation(
                12,
                "Флагман",
                "Хороший торговый центр. Все нужные магазины есть, парковка тоже имеется.",
                R.drawable.mall3,
                "ул. Удмуртская, 255б",
                4.7f
            ),
        )
    )

    fun getCategories(): List<Category> = categories
    fun getRecommendations(categoryId: Int): List<Recommendation> =
        recommendations[categoryId] ?: emptyList()

    fun getRecommendationById(id: Int): Recommendation? =
        recommendations.flatMap { it.value }.find { it.id == id }
}