package com.junsebog.everpay.common

sealed class Resource<T> {
    class Loading<T>(val message: String = ""): Resource<T>()
    data class Success<T>(val data: T): Resource<T>()
    data class Failure<T>(val message: String = "", val data: T? = null): Resource<T>()
}