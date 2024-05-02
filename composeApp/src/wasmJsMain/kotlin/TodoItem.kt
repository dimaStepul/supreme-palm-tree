package Service

import Item
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*

@Composable
fun TodoItem(todo: Item, onToggle: (Int) -> Unit, onDelete: (Int) -> Unit, onEdit: (Int, String) -> Unit) {
    var editing by remember { mutableStateOf(false) }
    var editText by remember { mutableStateOf(todo.task) }

    Column {
        if (editing) {
            TextField(
                value = editText,
                onValueChange = {
                    editText = it
                },
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = {
                        todo.id?.let { onEdit(it, editText) }
                        editing = false
                    }
                )
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = todo.completed,
                    onCheckedChange = {
                        todo.id?.let { it1 -> onToggle(it1) }
                    }
                )
                Text(todo.task)
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { editing = true }
                ) {
                    Text("Edit")
                }
            }
        }

        Button(
            onClick = { todo.id?.let { onDelete(it) } }
        ) {
            Text("Delete")
        }
    }
}
