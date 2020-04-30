package dk.offlines.loginwithrestapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id= intent.getStringExtra("Id")
        val username= intent.getStringExtra("Username")
        val first_name= intent.getStringExtra("First_name")
        val last_name= intent.getStringExtra("Last_name")

        textView_welcome.text = "Velkommen $first_name $last_name"
        textView_ID.text = id
        textView_username.text = username
    }
}
