package com.midcoreboot.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.app.AlertDialog
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class UpdateToDoActivity : Activity() {
    companion object {
        const val EXTRA_TODO_ID = "TODO_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_to_do)

        val button = findViewById<Button>(R.id.goBackButton3)
        button.setOnClickListener {
            //finish started activity.
            super.finish()
        }
        val todoId = intent.getIntExtra(EXTRA_TODO_ID, -1)
        if(todoId != -1) {
            val todo = toDoRepository.getToDoById(todoId)
            val title = findViewById<TextView>(R.id.title_editText2)
            val content = findViewById<TextView>(R.id.content_editText2)
            if(todo != null) {
                val todoTitle: String = todo.title
                val todoContent: String = todo.content
                title.setText(todoTitle)
                title.setGravity(Gravity.CENTER)
                content.setText(todoContent)

                val updateButton = findViewById<Button>(R.id.UpdateButton)
                updateButton.setOnClickListener {
                    //update
                    //validate()
                    val titleText = title.editableText.toString()
                    val contentText = content.editableText.toString()
                    if(titleText.length < 4) {
                        val titleLonger = R.string.validateTitleLonger
                        title.setText(titleLonger)
                    } else if (titleText.length > 24) {
                        val titleShorter = R.string.validateTitleShorter
                        title.setText(titleShorter)
                    } else if (contentText.length < 4) {
                        val contentLonger = R.string.validateContentLonger
                        content.setText(contentLonger)
                    } else if (contentText.length > 155) {
                        val contentShorter = R.string.validateContentShorter
                        content.setText(contentShorter)
                    } else {
                        toDoRepository.updateToDoById(todoId, title.editableText.toString(), content.editableText.toString())
                        super.finish()
                    }
                }
                val deleteButton = findViewById<Button>(R.id.DeleteButton2)
                deleteButton.setOnClickListener {
                    //DELETE
                    AlertDialog.Builder(this)
                        .setTitle("Delete ToDo")
                        .setMessage("Do you really want to delete it?")
                        .setPositiveButton(
                            "Yes"
                        ) { dialog, whichButton ->
                            // Delete it.
                            toDoRepository.deleteToDoById(todoId)
                            super.finish()
                        }.setNegativeButton(
                            "No"
                        ) { dialog, whichButton ->
                            // Do not delete it.
                        }.show()
                }
            }
        }
    }
}