package net.noncore.reportify.ui.composables.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState

@Composable
fun AppWindow(application: ApplicationScope) {
    Window(
        onCloseRequest = { application.exitApplication() },
        title = "Reportify",
        state = rememberWindowState(),
    ) {
        App()
    }
}
