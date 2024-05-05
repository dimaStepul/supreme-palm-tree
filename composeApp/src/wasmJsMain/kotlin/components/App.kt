package components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import data.Item
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val LightColorPalette = lightColors()

private val DarkColorPalette = darkColors()

@Composable
fun ComposeAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        content = content
    )
}

@Composable
fun ThemeToggleButton(onToggleTheme: () -> Unit) {
    IconButton(onClick = onToggleTheme) {
        Icon(Icons.Default.DarkMode, contentDescription = "Toggle Theme")
    }
}

@Composable
fun App() {
    var isDarkTheme by remember { mutableStateOf(false) }
    val editingTaskId by remember { mutableStateOf<Int?>(null) }
    val savedTodos by remember {
        mutableStateOf(
            Json.decodeFromString<List<Item>>(
                window.localStorage.getItem("todos") ?: "[]"
            )
        )
    }
    val todos by remember { mutableStateOf(savedTodos) }
    val showCompleted by remember { mutableStateOf(false) }
    LaunchedEffect(todos) {
        window.localStorage.setItem("todos", Json.encodeToString(todos))
    }
    val toggleTheme: () -> Unit = { isDarkTheme = !isDarkTheme }
    ComposeAppTheme(
        darkTheme = isDarkTheme
    ) {
        Surface(
            shape = RectangleShape,
            color = MaterialTheme.colors.surface,
        ) {
            AppContent(
                toggleTheme = toggleTheme,
                todos = mutableStateOf(todos),
                showCompleted = mutableStateOf(showCompleted),
                editingTaskId = mutableStateOf(editingTaskId)
            )
        }
    }
}


@Composable
fun AppContent(
    toggleTheme: () -> Unit,
    todos: MutableState<List<Item>>,
    showCompleted: MutableState<Boolean>,
    editingTaskId: MutableState<Int?>
) {
    DisposableEffect(todos.value) {
        window.localStorage.setItem("todos", Json.encodeToString(todos.value))
        onDispose { }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        ThemeToggleButton(onToggleTheme = toggleTheme)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {

            AddTodo { task ->
                todos.value = todos.value.toMutableList() + Item(todos.value.size, task, false)
            }
            ShowButton(
                showCompleted.value,
                onShowButtonClick = { showCompleted.value = !showCompleted.value })

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                TodoListContent(showCompleted.value, todos.value, editingTaskId.value,
                    onToggle = { id ->
                        todos.value = todos.value.map {
                            if (it.id == id) {
                                it.copy(completed = !it.completed)
                            } else {
                                it
                            }
                        }
                    },
                    onDelete = { id ->
                        todos.value = todos.value.filter { it.id != id }
                    }, onEdit = { id, newTask ->
                        todos.value = todos.value.map {
                            if (it.id == id) {
                                it.copy(task = newTask)
                            } else {
                                it
                            }
                        }
                    },
                    onEditingTaskChange = { newId ->
                        editingTaskId.value = newId
                    })

            }
            ClearButton(onClick = { todos.value = emptyList() })
        }
    }
}


@Composable
fun ShowButton(showCompleted: Boolean, onShowButtonClick: () -> Unit) {
    Button(
        onClick = onShowButtonClick
    ) {
        Text(if (!showCompleted) "Show Completed" else "Show Incomplete")
    }
}

@Composable
fun TodoListContent(
    showCompleted: Boolean,
    todos: List<Item>,
    editingTaskId: Int?,
    onToggle: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onEdit: (Int, String) -> Unit,
    onEditingTaskChange: (Int?) -> Unit
) {
    if (showCompleted && todos.any { it.completed } ||
        !showCompleted && todos.any { !it.completed }) {
        Surface(
            modifier = Modifier.padding(16.dp).width(800.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.surface,
            elevation = 3.dp,
        ) {
            TodoList(
                todos = todos,
                onToggle = onToggle,
                onDelete = onDelete,
                onEdit = onEdit,
                showCompleted = showCompleted,
                editingTaskId = editingTaskId,
                onEditingTaskChange = onEditingTaskChange
            )
        }
    }
}

@Composable
fun ClearButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(vertical = 16.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.Red)
    ) {
        Text("Clear All Tasks", color = Color.White)
    }
}
