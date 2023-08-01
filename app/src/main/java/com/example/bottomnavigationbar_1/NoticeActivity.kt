package com.example.bottomnavigationbar_1

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.main_bottom.R

class NoticeActivity : AppCompatActivity() {

    private lateinit var noticeListView: ListView
    private lateinit var adapter: NoticeListAdapter
    private lateinit var noticeList: MutableList<Notice>
    companion object {
        var userID: String? = null
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notice_activity)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        userID = intent.getStringExtra("userID")

        noticeListView = findViewById(R.id.noticeListView)
        noticeList = mutableListOf()
        adapter = NoticeListAdapter(applicationContext, noticeList)
        noticeListView.adapter = adapter

        // Load data from the database and populate the noticeList
        loadNoticeDataFromDB()

        val notice = findViewById<LinearLayout>(R.id.notice)

        // Click event for noticeListView to show NoticeList activity with selected notice data
        noticeListView.setOnItemClickListener { _, _, position, _ ->
            val selectedNotice = noticeList[position]
            val intent = Intent(this, NoticeList::class.java).apply {
                putExtra("noticeTitle", selectedNotice.notice)
                putExtra("noticeName", selectedNotice.name)
                putExtra("noticeDate", selectedNotice.date)
                putExtra("noticeContent", selectedNotice.content)
            }
            startActivity(intent)
        }
    }

    @SuppressLint("Range")
    private fun loadNoticeDataFromDB() {
        val dbPath = "/data/data/com.example.bottomnavigationbar_1/databases/notice.db"
        val db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)

        val query = "SELECT * FROM NOTICE"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val noticeTitle = cursor.getString(cursor.getColumnIndex("noticeTitle"))
                val noticeName = cursor.getString(cursor.getColumnIndex("noticeName"))
                val noticeDate = cursor.getString(cursor.getColumnIndex("noticeDate"))
                val noticeContent = cursor.getString(cursor.getColumnIndex("noticeContent"))

                val notice = Notice(noticeTitle, noticeName, noticeDate, noticeContent)
                noticeList.add(notice)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        adapter.notifyDataSetChanged()
    }

    private fun setButtonColors(vararg buttons: Button) {
        val colorPrimary = resources.getColor(com.google.android.material.R.color.design_default_color_primary)
        for (button in buttons) {
            button.setBackgroundColor(colorPrimary)
        }
    }
}
