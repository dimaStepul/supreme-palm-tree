import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    var todos by remember { mutableStateOf(listOf<Item>()) }
    var showCompleted by remember { mutableStateOf(true) }
//    var visible by remember {
//        mutableStateOf(true)
//    }
//    val animatedAlpha by animateFloatAsState(
//        targetValue = if (visible) 1.0f else 0f,
//        label = "alpha"
//    )
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
//                        .graphicsLayer {
//                            alpha = animatedAlpha
//                        }
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



