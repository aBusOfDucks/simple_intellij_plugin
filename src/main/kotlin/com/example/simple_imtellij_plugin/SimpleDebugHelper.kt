package com.example.simple_imtellij_plugin

import com.intellij.codeInsight.hints.presentation.scaleByFont
import com.intellij.internal.ui.uiDslShowcase.demoComponentLabels
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.ui.dsl.builder.panel
import com.intellij.util.ui.drawImage
import org.apache.commons.lang.ObjectUtils.Null
import java.awt.Canvas
import javax.swing.JComponent

private const val motivationalMessageText = "You can do it!"
private const val motivationalMessageTitle = "You got it!"

class SimpleDebugHelper : AnAction(){
    override fun actionPerformed(e: AnActionEvent) {
        MyPanelWrapper().show()
    }
}

private class MyPanelWrapper : DialogWrapper(true){
    init {
        init()
        title = motivationalMessageTitle
    }

    override fun createCenterPanel(): JComponent = myDialogPanel()

    fun myDialogPanel() : DialogPanel = panel {
        row {
            label(motivationalMessageText)
        }
    }

}