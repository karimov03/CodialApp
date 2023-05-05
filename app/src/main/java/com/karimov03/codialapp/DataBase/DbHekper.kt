package com.karimov03.codialapp.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHekper(context: Context):SQLiteOpenHelper(context, db_name,null, db_version) {

    companion object{
        const val db_name="mydatabase"
        const val db_version=1

        const val kurs_table="kurs_table"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val kurslarquary="create table "
            db?.execSQL(kurslarquary)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}