package com.startzplay.base

import com.startzplay.common.APIStates
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

abstract class BaseRepo(){


    fun <T> genericApiResponse(data: Response<T>): APIStates<T> {
        return if (data.isSuccessful) {
            if (data.body() != null) {
                APIStates.success(data.body())
            } else {
                APIStates.error(null, getErrorMessage(data.errorBody()))
            }
        } else {
            if (data.errorBody() != null) {
                APIStates.error(null, getErrorMessage(data.errorBody()))
            } else {
                return APIStates.error(null, data.message())
            }

        }
    }

    private fun getErrorMessage(body: ResponseBody?): String {
        var errorMessage = "Something went wrong"
        if (body!=null){
            val errorBody = JSONObject(body.string())
            if (errorBody.has("message")) {
                errorMessage = errorBody.getString("message")
            }
        }
        return errorMessage
    }
}