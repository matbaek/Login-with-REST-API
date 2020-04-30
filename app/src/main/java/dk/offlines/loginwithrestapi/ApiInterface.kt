package dk.offlines.loginwithrestapi

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    fun login(@Field("username") username: String, @Field("password") password: String): Call<ApiResponseUser>
}