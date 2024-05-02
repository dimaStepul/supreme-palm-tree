import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.unit.dp

@Composable
fun AddTodo(onAdd: (String) -> Unit) {
    var newTodo by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = newTodo,
            onValueChange = { newTodo = it },
            label = { Text("Add Todo") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                onAdd(newTodo)
                newTodo = ""
            }
        ) {
            Text("Add")
        }
    }
}

