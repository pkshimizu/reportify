package net.noncore.reportify

import androidx.compose.ui.window.application
import net.noncore.reportify.ui.composables.app.AppWindow

fun main() =
    application {
        AppWindow(this)
    }
