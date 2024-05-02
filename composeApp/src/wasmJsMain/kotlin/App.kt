import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.AddTodo
import components.TodoList
import data.Item
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun App() {
    val savedTodos by remember {
        mutableStateOf(
            Json.decodeFromString<List<Item>>(
                window.localStorage.getItem("todos") ?: "[]"
            )
        )
    }
    var todos by remember { mutableStateOf(savedTodos) }
    var showCompleted by remember { mutableStateOf(true) }
    LaunchedEffect(todos) {
        window.localStorage.setItem("todos", Json.encodeToString(todos))
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.surface
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                AddTodo { task ->
                    todos = todos + Item(todos.size, task, false)
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier.padding(16.dp, bottom = 16.dp),
                        onClick = { showCompleted = !showCompleted }
                    ) {
                        Text(if (showCompleted) "Show Incomplete" else "Show Completed")
                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TodoList(
                        todos = todos,
                        onToggle = { id ->
                            todos = todos.map {
                                if (it.id == id) {
                                    it.copy(completed = !it.completed)
                                } else {
                                    it
                                }
                            }
                        },
                        onDelete = { id ->
                            todos = todos.filter { it.id != id }
                        },
                        onEdit = { id, newTask ->
                            todos = todos.map {
                                if (it.id == id) {
                                    it.copy(task = newTask)
                                } else {
                                    it
                                }
                            }
                        },
                        showCompleted = showCompleted
                    )
                }
            }
        }
    }
}


