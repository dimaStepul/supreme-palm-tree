package components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.Item


@Composable
fun TodoItem(
    todo: Item,
    onToggle: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onEdit: (Int, String) -> Unit
) {
    var editing by remember { mutableStateOf(false) }
    var editText by remember { mutableStateOf(todo.task) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = {
                    onToggle(todo.id ?: -1)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (editing) {
                TextField(
                    value = editText,
                    onValueChange = {
                        editText = it
                    },
                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onEdit(todo.id ?: -1, editText)
                            editing = false
                        }
                    ),
                    modifier = Modifier.width(100.dp)
                )
                IconButton(
                    onClick = {
                        onEdit(todo.id ?: -1, editText)
                        editing = false
                    }
                ) {
                    Icon(Icons.Default.Done, contentDescription = "Done")
                }
            } else {
                Text(
                    text = todo.task,
                    modifier = Modifier.width(100.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = { editing = true }
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(
                    onClick = { onDelete(todo.id ?: -1) }
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}