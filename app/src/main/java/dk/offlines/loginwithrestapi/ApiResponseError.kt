package dk.offlines.loginwithrestapi

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ApiResponseError {
    @SerializedName("message")
    @Expose
    var error: String? = null
}