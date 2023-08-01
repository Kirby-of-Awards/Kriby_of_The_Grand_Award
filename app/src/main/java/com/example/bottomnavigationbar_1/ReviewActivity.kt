package com.example.bottomnavigationbar_1
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.main_bottom.R

class ReviewActivity : AppCompatActivity() {

    private lateinit var reviewListView: ListView
    private lateinit var adapter: ReviewListAdapter
    private lateinit var reviewList: MutableList<String>

    companion object {
        var userID: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_activity)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        userID = intent.getStringExtra("userID")

        reviewListView = findViewById(R.id.reviewListView)
        reviewList = mutableListOf()
        adapter = ReviewListAdapter(this, reviewList)
        reviewListView.adapter = adapter

        loadReviewDataFromDB()
    }

    @SuppressLint("Range")
    private fun loadReviewDataFromDB() {
        val dbManager = DBManager(this, "reviewTBL", null, 1)
        val db = dbManager.readableDatabase

        val query = "SELECT * FROM reviewTBL"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val foodName = cursor.getString(cursor.getColumnIndex("gName"))
                val review = cursor.getString(cursor.getColumnIndex("gReview"))

                val reviewData = "음식 이름: $foodName\n리뷰: $review"
                reviewList.add(reviewData)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        adapter.notifyDataSetChanged()
    }
}
