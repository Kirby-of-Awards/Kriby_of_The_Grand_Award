package com.example.bottomnavigationbar_1
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.main_bottom.R

class ReviewListAdapter(private val context: Context, private val reviewList: List<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return reviewList.size
    }

    override fun getItem(i: Int): Any {
        return reviewList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        var v = view
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.review, viewGroup, false)
        }

        val reviewText = v!!.findViewById<TextView>(R.id.reviewText)

        val reviewData = reviewList[i]
        reviewText.text = reviewData

        return v
    }
}

