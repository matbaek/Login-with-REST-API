package dk.offlines.loginwithrestapi

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    lateinit var id: String
    @SerializedName("username")
    lateinit var username: String
    @SerializedName("first_name")
    lateinit var first_name: String
    @SerializedName("last_name")
    lateinit var last_name: String
}