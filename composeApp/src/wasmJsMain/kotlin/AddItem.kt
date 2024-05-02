import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.foundation.*
import androidx.compose.material.*
import androidx.compose.ui.unit.dp

@Composable
fun AddTodo(onAdd: (String) -> Unit) {
    var newTodo by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
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
}
