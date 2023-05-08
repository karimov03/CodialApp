package com.karimov03.codialapp.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.karimov03.codialapp.Class.Kurslar
import com.karimov03.codialapp.Class.Mentor

class DbHelper(context: Context):SQLiteOpenHelper(context, db_name,null, db_version),DbInterface {

    companion object{
        const val db_name="my_sqldatabase"
        const val db_version=1

        const val kurs_table="kurs_table"
        const val kurs_id="kurs_id"
        const val kurs_name="kurs_name"
        const val kurs_about="kurs_about"


        const val mentor_table="mentor_table"
        const val mentor_id="mentor_id"
        const val mentor_name="mentor_name"
        const val mentor_father="mentor_father"
        const val mentor_kursi="mentor_kursi"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val kurslarquary="create table $kurs_table($kurs_id integer not null primary key autoincrement unique,$kurs_name text not null, $kurs_about text not null)"
            db?.execSQL(kurslarquary)
        val mentorquary = "create table $mentor_table($mentor_id integer not null primary key autoincrement unique,$mentor_name text not null, $mentor_father text not null,$mentor_kursi text not null )"
        db?.execSQL(mentorquary)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun AddKurs(kurslar: Kurslar) {
        val data=writableDatabase
        val contentValues=ContentValues()
        contentValues.put(kurs_name, kurslar.name)
        contentValues.put(kurs_about, kurslar.about)
        data.insert(kurs_table,null,contentValues)
    }

    override fun getAllKurs(): List<Kurslar> {
        val data=readableDatabase
        val quary="select * from $kurs_table"
        val cursor=data.rawQuery(quary,null)
        val list=ArrayList<Kurslar>()
        if (cursor.moveToFirst()){
            do {
                list.add(
                    Kurslar(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            }while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    override fun addMentor(mentor: Mentor) {
        val data=writableDatabase
        val contentValues=ContentValues()
        contentValues.put(mentor_name, mentor.name)
        contentValues.put(mentor_father, mentor.father)
        contentValues.put(mentor_kursi, mentor.kursi)
        data.insert(mentor_table,null,contentValues)
    }

    override fun editMentor(id: Int, mentor: Mentor) {
        val data = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(mentor_name, mentor.name)
        contentValues.put(mentor_father, mentor.father)
        contentValues.put(mentor_kursi, mentor.kursi)
        data.update(mentor_table, contentValues, "$mentor_id=?", arrayOf(id.toString()))
    }


    override fun deleteMentor(id: Int) {
        val data = writableDatabase
        data.delete(mentor_table, "$mentor_id = ?", arrayOf(id.toString()))
    }


    override fun getAllMentor(string: String): List<Mentor> {
        val data = readableDatabase
        val query = "SELECT * FROM mentor_table WHERE mentor_kursi = ?"
        val cursor = data.rawQuery(query, arrayOf(string))
        val list = ArrayList<Mentor>()
        if (cursor.moveToFirst()) {
            do {
                val mentor = Mentor(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
                list.add(mentor)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }



}