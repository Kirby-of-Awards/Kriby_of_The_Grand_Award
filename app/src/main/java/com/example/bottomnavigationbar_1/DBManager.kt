package com.example.bottomnavigationbar_1
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.speech.RecognitionListener

class DBManager(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE User (id text PRIMARY KEY, name text, password text)")
        db!!.execSQL("CREATE TABLE reviewTBL (gName CHAR(20) PRIMARY KEY,gReview CHAR(100));")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS User")
        db!!.execSQL("DROP TABLE IF EXISTS reviewTBL")

        onCreate(db)
    }

    fun insertUser(name: String, id: String, password: String) {
        var db = this.writableDatabase

        db.execSQL("INSERT INTO User VALUES('" + name + "'" + ", '" + id + "'" + ", '" + password + "');")
        db.close()
    }
    fun insertReview(foodName: String, reviewbox: String) {
        var db = this.writableDatabase

        db.execSQL("INSERT INTO reviewTBL VALUES('"+foodName+"','"+reviewbox+"');")
        db.close()
    }
}