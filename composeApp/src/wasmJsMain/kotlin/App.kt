import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun App() {
    var todos by remember { mutableStateOf(listOf<Item>()) }
    var showCompleted by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.surface
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AddTodo { task ->
                todos = todos + Item(todos.size, task, false)
            }

            Button(
                modifier = Modifier.padding(vertical = 8.dp),
                onClick = { showCompleted = !showCompleted }
            ) {
                Text(if (showCompleted) "Show Incomplete" else "Show Completed")
            }

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


//@Composable
//fun App() {
//    var todos by remember { mutableStateOf(listOf<Item>()) }
//    var showCompleted by remember { mutableStateOf(true) }
//
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Surface(
//            modifier = Modifier.padding(16.dp),
//            shape = MaterialTheme.shapes.medium,
//            color = MaterialTheme.colors.surface
//        ) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                AddTodo { task ->
//                    todos = todos + Item(todos.size, task,false)
//                }
//
//                Button(
//                    modifier = Modifier.padding(vertical = 8.dp),
//                    onClick = { showCompleted = !showCompleted }
//                ) {
//                    Text(if (showCompleted) "Show Incomplete" else "Show Completed")
//                }
//
//                TodoList(
//                    todos = todos,
//                    onToggle = { id ->
//                        todos = todos.map {
//                            if (it.id == id) {
//                                it.copy(completed = !it.completed)
//                            } else {
//                                it
//                            }
//                        }
//                    },
//                    onDelete = { id ->
//                        todos = todos.filter { it.id != id }
//                    },
//                    onEdit = { id, newTask ->
//                        todos = todos.map {
//                            if (it.id == id) {
//                                it.copy(task = newTask)
//                            } else {
//                                it
//                            }
//                        }
//                    },
//                    showCompleted = showCompleted
//                )
//            }
//        }
//    }
//}


