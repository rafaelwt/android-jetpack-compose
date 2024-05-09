package com.example.myjatpackcomponents.ui.theme

data class CheckInfo(val title: String, val selected: Boolean,  var onCheckedChange: (Boolean) -> Unit)
