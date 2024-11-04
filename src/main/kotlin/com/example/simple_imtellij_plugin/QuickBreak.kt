package com.example.simple_imtellij_plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.ui.Messages
import kotlin.math.min


private const val duckEmoji = "\uD83E\uDD86"
private const val RPSdialoge = "What do you choose?"
private const val RPSTitle = "Quick break!"
private val RPSOptions = arrayOf("Rock! \uD83E\uDEA8", "Paper! \uD83D\uDCC3", "Scissors! ✂\uFE0F")

private val winTitle = "You won!"
private val winMessage = "You are the best!"

private val loseTitle = "You lost!"
private val loseMessage = "Better luck next time!"

private val drawTitle = "Draw!"
private val drawMessage = "What are the odds?!"

private val exitMessage = "Do you want to play again?"
private val exitOptions = arrayOf("Yes!", "No :(")

private val rewardLine = "\uD83C\uDFC6\n"

class QuickBreak : AnAction(){
    override fun actionPerformed(e: AnActionEvent) {
        while(true) {
            var playerPick = Messages.showDialog(RPSdialoge + duckEmoji, RPSTitle, RPSOptions, (0..2).random(), null)
            if(playerPick < 0 || playerPick > 2) {
                break
            }
            val duckPick = (0..2).random()
            val duckMessage = "I chose " + RPSOptions[duckPick]
            var message = drawMessage
            var title = drawTitle
            when(result(playerPick, duckPick)) {
                1 -> {
                    message = winMessage
                    title = winTitle
                    reward(e)
                }
                0 -> {
                    message = drawMessage
                    title = drawTitle
                }
                -1 -> {
                    message = loseMessage
                    title = loseTitle
                    penalty(e)
                }
            }
            message = duckMessage + "\n" + message + "\n" + exitMessage
            playerPick = Messages.showDialog(message, title, exitOptions, 0, null)
            if(playerPick != 0)
                break
        }
    }

    // Rewards user with trophy emoji
    private fun reward(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)

        if (editor != null && project != null) {
            val document = editor.document
            val runnable = Runnable { document.insertString(0, rewardLine) }
            WriteCommandAction.runWriteCommandAction(project, runnable)
        }
    }

    // Punishes user by deleting random fragment of the code
    private fun penalty(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)

        if (editor != null && project != null) {
            val documentRange = editor.document.text.length
            val start = (0..documentRange).random()
            val end = min(documentRange, start + (0..50).random())
            val runnable = Runnable { editor.document.deleteString(start, end) }
            WriteCommandAction.runWriteCommandAction(project, runnable)
        }
    }

    // Returns result od rock paper scissors game:
    // 1 - player wins,
    // 0 - draw,
    // -1 - duck wins.
    private fun result(playerPick : Int, duckPick : Int) : Int {
        if(playerPick == duckPick)
            return 0
        if((playerPick + 1) % 3 == duckPick)
            return -1
        return 1
    }
}