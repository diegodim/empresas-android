package com.diegoduarte.desafio.data.model

data class LoginResponse (
    var success: Boolean = false,
    var error: ArrayList<String>?
)