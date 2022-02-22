package com.midcoreboot.todoapp

import android.app.Activity
import android.os.Bundle
import android.widget.*
import android.content.Intent

class CreateToDoActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createtodo)

        val button = findViewById<Button>(R.id.goBackButton)
        button.setOnClickListener {
            //finish started activity.
            super.finish()
        }

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
                val intent = Intent(this, ViewToDoActivity::class.java)
                intent.putExtra(ViewToDoActivity.EXTRA_TODO_ID, createdId)
                //intent.putExtraInt(ViewToDoActivity.EXTRA_TODO_ID, createdId)
                startActivity(intent)
                super.finish()
            }
        }
    }
}