package com.example.bottomnavigationbar_1
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.main_bottom.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)


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
}
