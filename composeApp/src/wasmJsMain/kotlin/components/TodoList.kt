package components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.Item

@Composable
fun TodoList(
    todos: List<Item>,
    onToggle: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onEdit: (Int, String) -> Unit,
    showCompleted: Boolean,
    editingTaskId: Int?,
    onEditingTaskChange: (Int?) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp).width(800.dp)
        ) {
            todos.filter { it.completed == showCompleted }.forEach { todo ->
                TodoItem(
                    todo = todo,
                    onToggle = onToggle,
                    onDelete = onDelete,
                    onEdit = onEdit,
                    editingTaskId = editingTaskId,
                    onEditingTaskChange = onEditingTaskChange
                )
            }
        }
    }
}
