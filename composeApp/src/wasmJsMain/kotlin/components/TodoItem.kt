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
    onEdit: (Int, String) -> Unit,
    editingTaskId: Int?,
    onEditingTaskChange: (Int?) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(900.dp)
        ) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = {
                    onToggle(todo.id ?: -1)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (editingTaskId == todo.id) {
                EditableTodo(
                    editText = todo.task,
                    onEdit = { newTask ->
                        onEdit(todo.id ?: -1, newTask)
                        onEditingTaskChange(null)
                    }
                )
            } else {
                DisplayTodo(
                    task = todo.task,
                    onEditClick = {
                        if (editingTaskId == null) {
                            onEditingTaskChange(todo.id)
                        } else {
                            onEditingTaskChange(null)
                            onEditingTaskChange(todo.id)
                        }
                    },
                    onDeleteClick = { onDelete(todo.id ?: -1) }
                )
            }
        }
    }
}


@Composable
fun EditableTodo(
    editText: String,
    onEdit: (String) -> Unit
) {
    var newTask by remember { mutableStateOf(editText) }

    TextField(
        value = newTask,
        onValueChange = { newTask = it.trimStart() },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                onEdit(newTask.trimStart())
            }
        ),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(56.dp)
//            .verticalScroll(rememberScrollState())
    )
    IconButton(
        onClick = { onEdit(newTask.trimStart()) }
    ) {
        Icon(Icons.Default.Done, contentDescription = "Done")
    }
}

@Composable
fun DisplayTodo(
    task: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = task,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(end = 8.dp)
        )
        IconButton(
            onClick = { onEditClick() }
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
        IconButton(
            onClick = { onDeleteClick() }
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
