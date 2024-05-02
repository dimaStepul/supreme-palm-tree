package components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@Composable
fun AddTodo(onAdd: (String) -> Unit) {
    var newTodo by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current

            OutlinedTextField(
                value = newTodo,
                onValueChange = { newTodo = it },
                label = { Text("Add Todo") },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .onKeyEvent {
                        if (it.key == Key.Enter && it.type == KeyEventType.KeyUp) {
                            onAdd(newTodo)
                            newTodo = ""
                            keyboardController?.hide()
                            true
                        } else {
                            false
                        }
                    }
            )
            Button(
                onClick = {
                    onAdd(newTodo)
                    newTodo = ""
                }
            ) {
                Text("Add")
            }
        }
    }
}