package com.rylov.weatherforecast.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ErrorMessage(message: String, onDismiss: () -> Unit) {
    var show by remember { mutableStateOf(true) }

    if (show) {
        AlertDialog(
            onDismissRequest = {
                show = false
                onDismiss()
            },
            title = { Text("Error") },
            text = { Text(message) },
            confirmButton = {
                Button(onClick = {
                    show = false
                    onDismiss()
                }) {
                    Text("OK")
                }
            }
        )
    }
}