package com.example.main_bottom

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import com.example.bottomnavigationbar_1.ModifyActivity
import com.example.bottomnavigationbar_1.NoticeActivity


class Setting : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val backBtn = findViewById<ImageButton>(R.id.backbtn)
        backBtn.setOnClickListener {
            onBackPressed()
        }
        val modifyBtn = findViewById<ImageButton>(R.id.imageButton)
        modifyBtn.setOnClickListener {

            val intent = Intent(this, ModifyActivity::class.java)
            startActivity(intent)
        }

        val announcementBtn = findViewById<ImageButton>(R.id.imageButton2)
        announcementBtn.setOnClickListener {

            val intent = Intent(this, NoticeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_phone_setting, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_recipe -> {
                val intent = Intent(this, Recipe::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_timer -> {
                val intent = Intent(this, Timer::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}