package com.midcoreboot.todoapp

import android.app.Activity
import android.os.Bundle
import android.widget.*
import android.content.Intent


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)// as ListView
        listView.adapter = ArrayAdapter<ToDo>(
            this,
            android.R.layout.simple_list_item_1,
            toDoRepository.getAllToDos()
        )
        listView.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position) as ToDo
            val intent = Intent(this, ViewToDoActivity::class.java)
            intent.putExtra(ViewToDoActivity.EXTRA_TODO_ID, element.id)
            startActivity(intent)
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            startActivity(Intent(this, CreateToDoActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val listView = this.findViewById<ListView>(R.id.listView)
        val adapt = listView.adapter as ArrayAdapter<*>?
        adapt?.notifyDataSetChanged()
        println(toDoRepository.getAllToDos())
    }
}