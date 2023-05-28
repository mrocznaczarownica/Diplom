package com.example.diplom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.diplom.database.DBHelper
import com.example.diplom.database.User

class LoginActivity : AppCompatActivity() {

    lateinit var sqlHelper:DBHelper
    public lateinit var log:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sqlHelper = DBHelper(this)
    }

    fun LoginButtonClick(view: View) {
        var login: EditText = findViewById(R.id.logineditText)
        var pass: EditText = findViewById(R.id.passEditText)
        log = login.toString()

        if (login.toString().isNotEmpty() && pass.toString().isNotEmpty()) {
            var us:Int = sqlHelper.autorisation(login.toString(), pass.toString())
            if(us >= 1){
                Toast.makeText(this,"ok",Toast.LENGTH_SHORT)
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("login1", log)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Неверный логин и/или пароль",Toast.LENGTH_SHORT)
                login.text.clear()
                pass.text.clear()
            }
        }
    }

    fun SignInLabelClick(view: View) {
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }
}
