package components

import App
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ScreenHandler() {
    var showWelcomeScreen by remember { mutableStateOf(true) }

    if (showWelcomeScreen) {
        WelcomeScreen(
            onQuickCreateNotesClicked = {
                showWelcomeScreen = false
            },
            onLoginClicked = { /* TODO: Add logic for login */ }
        )
    } else {
        App()
    }
}

@Composable
fun WelcomeScreen(
    onQuickCreateNotesClicked: () -> Unit,
    onLoginClicked: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onQuickCreateNotesClicked,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Quick notes")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onLoginClicked,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Login")
            }
        }
    }
}
