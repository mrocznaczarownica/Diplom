package com.example.diplom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.diplom.database.Clients
import com.example.diplom.database.DBHelper

class SignIn : AppCompatActivity() {

    lateinit var sqlHelper:DBHelper
    public lateinit var log:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun SignInButtonClick(view: View) {
        var firstName:EditText = findViewById(R.id.firstName)
        var name:EditText = findViewById(R.id.name)
        var lastName:EditText = findViewById(R.id.lastName)
        var phone:EditText = findViewById(R.id.phoneNumber)
        var login:EditText = findViewById(R.id.login)
        var pass:EditText = findViewById(R.id.pass)

        if (firstName.toString().isNotEmpty()&&name.toString().isNotEmpty()&&lastName.toString().isNotEmpty()
            &&phone.toString().isNotEmpty()&&login.toString().isNotEmpty()&&pass.toString().isNotEmpty()){
            val data = Clients(firstName.toString(), name.toString(), lastName.toString(), phone.toString(), pass.toString())

            sqlHelper = DBHelper(this)
            val success = sqlHelper.addClient(data)
            if(success !== null){
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("login", log)
                startActivity(intent)
                log = login.toString()
            }
            else {
                Toast.makeText(this, "Не удалось зарегистрировать", Toast.LENGTH_SHORT).show()
            }
        }
    }
}