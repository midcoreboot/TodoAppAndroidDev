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
        println("fromUpdate:"+ todoId)
        if(todoId != -1) {
            val todo = toDoRepository.getToDoById(todoId)
            val title = findViewById<TextView>(R.id.title_editText2)
            val content = findViewById<TextView>(R.id.content_editText2)
            if(todo != null) {
                val todoTitle: String = todo.title
                val todoContent: String = todo.content
                title.setText(todoTitle) //text = todoTitle
                title.setGravity(Gravity.CENTER);
                content.setText(todoContent) //text = todoContent

                val updateButton = findViewById<Button>(R.id.UpdateButton)
                updateButton.setOnClickListener {
                    //update
                    //validate()
                    val titleText = title.editableText.toString()
                    val contentText = content.editableText.toString()
                    if(titleText.length < 4) {
                        title.setText("Please choose a title longer than 3 chars.")
                    } else if (titleText.length > 24) {
                        title.setText("Please choose a title shorter than 24 chars.")
                    } else if (contentText.length < 4) {
                        content.setText("Please choose content longer than 3 chars")
                    } else if (contentText.length > 155) {
                        content.setText("Please choose content no longer than 155 chars.")
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

        /*
        val titleInput = findViewById<EditText>(R.id.title_editText2)
        val contentInput = findViewById<EditText>(R.id.content_editText2)
        val titleText = titleInput.editableText
        */

        /*
        val createButton = findViewById<Button>(R.id.createButton)
        createButton.setOnClickListener {
            //create task and redirect
            //Add validation:
            val titleInput = findViewById<EditText>(R.id.title_editText)
            val contentInput = findViewById<EditText>(R.id.content_editText)
            val titleText = titleInput.editableText.toString()
            val contentText = contentInput.editableText.toString()
            if(titleText.length < 4) {
                findViewById<EditText>(R.id.title_editText).setText("Please choose a title longer than 3 chars.")
            } else if (titleInput.length() > 24) {
                findViewById<EditText>(R.id.title_editText).setText("Please choose a title shorter than 24 chars.")
            } else if (contentText.length < 4) {
                findViewById<EditText>(R.id.content_editText).setText("Please choose content longer than 3 chars")
            } else if (contentText.length > 155) {
                findViewById<EditText>(R.id.content_editText).setText("Please choose content no longer than 155 chars.")
            } else {
                val createdId = toDoRepository.addToDo(titleText, contentText)
                val intent = Intent(this, ViewToDoActivity.Companion::class.java)
                intent.putExtra(ViewToDoActivity.EXTRA_TODO_ID, createdId)
                startActivity(intent)
                super.finish()
            }
        } */
    }
}