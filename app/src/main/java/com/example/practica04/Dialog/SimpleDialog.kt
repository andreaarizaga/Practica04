package com.example.practica04.Dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

// class SimpleDialog {

    @Composable
    fun SimpleDialog(
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit,
        dialogTitle: String,
        dialogText: String,
        show: Boolean
    ) {
        if (show) {
            AlertDialog(
                title = {
                    Text(text = dialogTitle)
                },
                text = {
                    Text(text = dialogText)
                },
                onDismissRequest = {
                    onDismissRequest()
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onConfirmation()
                        }
                    ) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            onDismissRequest()
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }

