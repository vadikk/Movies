package com.example.network

internal sealed interface ApiResult<T> {
    class Success<T>(val data: T) : ApiResult<T>
    class Error<T>(val code: Int, val message: String?) : ApiResult<T>
    class Exception<T>(val throwable: Throwable) : ApiResult<T>
}