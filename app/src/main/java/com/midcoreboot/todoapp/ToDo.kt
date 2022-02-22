package com.midcoreboot.todoapp

data class ToDo(
    val id: Int,
    var title: String,
    var content: String
) {
    override fun toString() = title
}