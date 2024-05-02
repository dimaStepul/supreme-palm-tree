package components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.foundation.*
import androidx.compose.ui.unit.dp
import data.Item

@Composable
fun TodoList(
    todos: List<Item>,
    onToggle: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onEdit: (Int, String) -> Unit,
    showCompleted: Boolean
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)
        ) {
            todos.filter { it.completed == showCompleted }.forEach { todo ->
                TodoItem(todo, onToggle, onDelete, onEdit)
            }
        }
    }
}