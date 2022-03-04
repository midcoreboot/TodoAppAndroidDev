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
                val titleLonger = R.string.validateTitleLonger
                findViewById<EditText>(R.id.title_editText).setText(titleLonger)
            } else if (titleInput.length() > 24) {
                val titleShorter = R.string.validateTitleShorter
                findViewById<EditText>(R.id.title_editText).setText(titleShorter)
            } else if (contentText.length < 4) {
                val contentLonger = R.string.validateContentLonger
                findViewById<EditText>(R.id.content_editText).setText(contentLonger)
            } else if (contentText.length > 155) {
                val contentShorter = R.string.validateContentShorter
                findViewById<EditText>(R.id.content_editText).setText(contentShorter)
            } else {
                val createdId = toDoRepository.addToDo(titleText, contentText)
                val intent = Intent(this, ViewToDoActivity::class.java)
                intent.putExtra(ViewToDoActivity.EXTRA_TODO_ID, createdId)
                startActivity(intent)
                super.finish()
            }
        }
    }
}