package dk.offlines.loginwithrestapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val apiInterface = ApiClient().getClient().create(ApiInterface::class.java)

        button.setOnClickListener {
            var username: EditText = findViewById(R.id.editText_username)
            var password: EditText = findViewById(R.id.editText_password)

            // Hide keyboard
            // https://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)

            val call: Call<ApiResponseUser> = apiInterface.login(username.text.toString(), password.text.toString())
            call.enqueue(object : Callback<ApiResponseUser> {
                @Override
                override fun onResponse(
                    call: Call<ApiResponseUser>,
                    response: Response<ApiResponseUser>
                ) {

                    Log.d("TAG", response.code().toString())
                    var message: String?

                    if (response.isSuccessful) {
                        var apiResponseUser: ApiResponseUser? = response.body()
                        message = apiResponseUser?.message
                        var user: User? = apiResponseUser?.user

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.putExtra("Id", user?.id)
                        intent.putExtra("Username", user?.username)
                        intent.putExtra("First_name", user?.first_name)
                        intent.putExtra("Last_name", user?.last_name)
                        startActivity(intent)
                        finish()
                    } else {
                        val error = response.errorBody()?.string()
                        val jsonObject = JsonParser().parse(error).asJsonObject
                        message = jsonObject.get("message").asString
                    }

                    Toast.makeText(
                        applicationContext,
                        message,
                        Toast.LENGTH_LONG
                    ).show()
                }

                @Override
                override fun onFailure(
                    call: Call<ApiResponseUser>,
                    t: Throwable?
                ) {
                    Toast.makeText(
                        applicationContext,
                        "Something went wrong",
                        Toast.LENGTH_LONG
                    ).show()
                    call.cancel()
                }
            })
        }
    }
}
