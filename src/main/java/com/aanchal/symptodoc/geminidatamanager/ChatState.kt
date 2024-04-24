package com.aanchal.symptodoc.geminidatamanager

data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = ""
)
