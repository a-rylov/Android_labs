package com.rylov.flightssearch

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rylov.flightssearch.data.FlightsDatabase
import com.rylov.flightssearch.ui.viewmodels.FlightsViewmodel
import com.rylov.flightssearch.ui.viewmodels.FlightsViewmodelFactory
import com.rylov.flightssearch.data.repository.SearchPrefsRepository
import com.rylov.flightssearch.ui.screens.FlightSearchApp
import com.rylov.flightssearch.ui.theme.FlightsSearchTheme

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = FlightsDatabase.getDatabase(this)
        val searchPreferences = SearchPrefsRepository(dataStore)

        setContent {
            FlightsSearchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: FlightsViewmodel = viewModel(
                        factory = FlightsViewmodelFactory(
                            database.airportDao(),
                            database.favoriteDao(),
                            searchPreferences
                        )
                    )

                    FlightSearchApp(viewModel = viewModel)
                }
            }
        }
    }
}