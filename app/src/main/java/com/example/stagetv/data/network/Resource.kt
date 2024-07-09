package com.example.stagetv.data.network

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {
    class Success<out T>(data: T, message: String) : Resource<T>(data, message)
    class Failure<out T>(
        data: T, message: String
    ) : Resource<T>(data, message)

    class Loading<out T>() : Resource<T>()
    class Unspecified<out T>() : Resource<T>()
    class AlreadySuccess<out T>(): Resource<T>()
}