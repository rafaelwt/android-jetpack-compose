package com.example.myjatpackcomponents

import android.app.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



@Composable
fun MySampleCustomDialog(
    showDialog: Boolean, // Track dialog visibility
    onDismiss: () -> Unit, // Function to call on dismissal
) {
    if (showDialog) {
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp)) // Dialog background
                .padding(16.dp) // Inner padding
                .clickable { onDismiss() } // Handle background clicks to dismiss
        ) {
            Column {
                Text("Dialog Title") // Dialog title
                Text("Dialog description") // Dialog description
                Button(onClick = onDismiss) { // Dismiss button
                    Text("Dismiss")
                }
            }
        }
    }
}
@Composable
fun MyDialog(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Dialog Title") },
            text = { Text(text = "Dialog description") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Dismiss")
                }
            }
        )
    }
}