package dk.offlines.loginwithrestapi

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

class ApiResponseUser {
    @SerializedName("message")
    lateinit var message: String
    @SerializedName("item")
    @Nullable
    lateinit var user: User
}