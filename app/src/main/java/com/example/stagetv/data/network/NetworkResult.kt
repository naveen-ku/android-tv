package com.example.stagetv.data.network

sealed class NetworkResult<out T>(val data: T? = null, val message: String? = null) {
    class Success<out T>(data: T, message: String) : NetworkResult<T>(data, message)
    class Failure<out T>(
        data: T? = null, message: String
    ) : NetworkResult<T>(data, message)

    class Loading<out T>() : NetworkResult<T>()
    class Unspecified<out T>() : NetworkResult<T>()
    class AlreadySuccess<out T>(): NetworkResult<T>()
}