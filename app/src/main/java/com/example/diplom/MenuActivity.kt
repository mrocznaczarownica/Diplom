package com.example.diplom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.diplom.usersActivities.CartActivity
import com.example.diplom.usersActivities.CatalogActivity
import com.example.diplom.usersActivities.CheckOrdersActivity
import com.example.diplom.usersActivities.ConsultationActivity
import com.example.diplom.usersActivities.CreateOrder
import com.example.diplom.usersActivities.MyProfileActivity

class MenuActivity : AppCompatActivity() {

    lateinit var nameUser:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        nameUser = findViewById(R.id.nameTextView)
        var log:String = intent.getStringExtra("login").toString()
        var log1:String = intent.getStringExtra("login1").toString()
        if (log.isNotEmpty()){
            nameUser.text = log
        } else if (log1.isNotEmpty()){
            nameUser.text = log1
        }else{
            nameUser.setText("")
        }
    }

    fun catalogButtonClick(view: View) {
        val intent = Intent(this, CatalogActivity::class.java)
        startActivity(intent)
    }

    fun createOrderButtonClick(view: View) {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

    fun checkOrdersButtonClick(view: View) {
        val intent = Intent(this, CheckOrdersActivity::class.java)
        startActivity(intent)
    }

    fun cartButtonClick(view: View) {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

    fun consultationButtonClick(view: View) {
        val intent = Intent(this, ConsultationActivity::class.java)
        startActivity(intent)
    }

    fun myProfileButtonClick(view: View) {
        val intent = Intent(this, MyProfileActivity::class.java)
        intent.putExtra("login", nameUser.text.toString())
        startActivity(intent)
    }
}