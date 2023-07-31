package com.example.guru2

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
        db!!.execSQL("CREATE TABLE Directory (dirname text, endDate text)")
        db!!.execSQL("CREATE TABLE File (fileName text PRIMARY KEY, dirName text, addFile text, memo text)")
        db!!.execSQL("CREATE TABLE TodoList(data DATE not null, list text, isChecked int)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS User")
        db!!.execSQL("DROP TABLE IF EXISTS Directory")
        db!!.execSQL("DROP TABLE IF EXISTS File")
        db!!.execSQL("DROP TABLE IF EXISTS TodoList")
        onCreate(db)
    }

    fun insertUser(name: String, id: String, password: String) {
        var db = this.writableDatabase

        db.execSQL("INSERT INTO User VALUES('" + name + "'" + ", '" + id + "'" + ", '" + password + "');")
        db.close()
    }
}