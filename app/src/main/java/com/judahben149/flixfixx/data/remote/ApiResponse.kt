package com.judahben149.flixfixx.data.remote

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T): ApiResponse<T>()
    data class Error(val exception: String): ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}
