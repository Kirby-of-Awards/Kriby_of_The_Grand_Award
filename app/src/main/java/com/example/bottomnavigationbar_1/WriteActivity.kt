package com.example.bottomnavigationbar_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.main_bottom.R


class WriteActivity : AppCompatActivity() {
    lateinit var review_backbtn: ImageButton
    lateinit var registerbutton: Button
    lateinit var reviewbox: EditText
    lateinit var foodName: EditText
    lateinit var review_btn: ImageButton
    var TAG: String = "WriteReview"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_write)


        review_backbtn = findViewById(R.id.review_backbtn)
        review_backbtn.setOnClickListener() {
            finish()
        }

        registerbutton = findViewById(R.id.registerbutton)
        reviewbox = findViewById(R.id.reviewbox)
        foodName = findViewById(R.id.foodName)

        var dbManager = DBManager(this, "reviewTBL", null, 1)





        registerbutton.setOnClickListener {

            val foodname = foodName.text.toString()
            val reviewbox = reviewbox.text.toString()

            dbManager.insertReview(foodname, reviewbox)
            Toast.makeText(this, "입력 완료!", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "리뷰 데이터 삽입")
            finish()

        }


    }
}




