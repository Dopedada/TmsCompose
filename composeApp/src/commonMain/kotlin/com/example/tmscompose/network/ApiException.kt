package com.example.tmscompose.network

class ApiException(var code: Int, override var message: String) : RuntimeException()