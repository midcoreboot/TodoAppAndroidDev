package com.midcoreboot.todoapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.*

//const val EXTRA_TODO_ID = "EXTRA_TODO_ID"

class ViewToDoActivity : Activity() {
    companion object {
        const val EXTRA_TODO_ID = "TODO"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewtodo)

        val goBackButton = findViewById<Button>(R.id.goBackButton2)
        goBackButton.setOnClickListener {
            super.finish()
        }
        print("test2")
        val todoId = intent.getIntExtra(EXTRA_TODO_ID, 0)
        print(todoId)
        if(todoId != 0) {
            val todo = toDoRepository.getToDoById(todoId)

            val title = findViewById<TextView>(R.id.titleText)
            val content = findViewById<TextView>(R.id.contentText)
            println(todo?.title + ":" + todo?.content)
            if (todo != null) {
                val todoTitle: String = todo.title
                val todoContent: String = todo.content
                title.text = todoTitle
                title.setGravity(Gravity.CENTER);
                content.text = todoContent
            }
            val editButton = findViewById<Button>(R.id.EditButton)
            editButton.setOnClickListener {

                val intent = Intent(this, UpdateToDoActivity::class.java)
                intent.putExtra(UpdateToDoActivity.EXTRA_TODO_ID, todoId)
                startActivity(intent)
            }
            val deleteButton = findViewById<Button>(R.id.DeleteButton)
            deleteButton.setOnClickListener {
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
    override fun onResume() {
        super.onResume()/*
        val content = findViewById<TextView>(R.id.contentText)
        val listView = this.findViewById<ListView>(R.id.listView)
        val adapt = listView.adapter as ArrayAdapter<*>?
        adapt?.notifyDataSetChanged()*/
        val id = intent.getIntExtra(EXTRA_TODO_ID, 0)
        //if(id != -1) {
            val todo = toDoRepository.getToDoById(id)
            val title = findViewById<TextView>(R.id.titleText)
            val content = findViewById<TextView>(R.id.contentText)
            if (todo != null) {
                title.text = todo.title
                content.text = todo.content
            }
        //}
    }
}