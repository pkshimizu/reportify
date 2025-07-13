package net.noncore.reportify.ui.windows.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState

@Composable
fun MainWindow(application: ApplicationScope) {
    Window(
        onCloseRequest = { application.exitApplication() },
        title = "Reportify",
        state = rememberWindowState(),
    ) {
        App()
    }
}
