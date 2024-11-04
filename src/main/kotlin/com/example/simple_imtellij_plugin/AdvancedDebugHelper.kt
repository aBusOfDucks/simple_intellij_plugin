package com.example.simple_imtellij_plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages


private const val duckEmoji = "\uD83E\uDD86"
private const val duckMessage = "I am here for you"
private const val duckTitle = "Quack!"
private val dialogOptions = arrayOf("Thank you for your time!")

// Inspired by:
// https://en.wikipedia.org/wiki/Rubber_duck_debugging

class AdvancedDebugHelper : AnAction(){
    override fun actionPerformed(e: AnActionEvent) {
        Messages.showDialog(duckMessage + duckEmoji, duckTitle, dialogOptions, 0, null)
    }
}