import Service.TodoItem
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*

@Composable
fun TodoList(
    todos: List<Item>,
    onToggle: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onEdit: (Int, String) -> Unit,
    showCompleted: Boolean
) {
    Column {
        todos.filter { it.completed == showCompleted }.forEach { todo ->
            TodoItem(todo, onToggle, onDelete, onEdit)
        }
    }
}
