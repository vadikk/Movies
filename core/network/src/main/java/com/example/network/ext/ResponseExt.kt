package com.example.network.ext

import com.example.network.ApiResult
import retrofit2.HttpException

internal fun <T> T.parseResponse(): ApiResult<T>{
    return try {
        val result = this
        ApiResult.Success(result)
    }catch (e: HttpException){
        val errorResponse = e.response()?.raw()
        val errorCode = errorResponse?.code ?: 0
        val errorMessage = errorResponse?.message.orEmpty()
        ApiResult.Error(errorCode, errorMessage)
    }catch (e: Exception){
        ApiResult.Exception(e)
    }
}

internal fun <T> T.parseResult(): Result<T>{
    return try {
        val result = this
        Result.success(result)
    }catch (e: Exception){
        Result.failure(e)
    }
}