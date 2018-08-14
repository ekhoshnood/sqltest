package com.example.a259.sqltest

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MYDATABASE"
val TABLE_NAME = "Contacts"
val COL_NAME = "FirstName"
val COL_LASTNAME = "LastName"
val COL_PHONENUMBER = "PhoneNumber"
val COL_ID = "id"

class DatabaseHandler (var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_LASTNAME + " VARCHAR(256)," +
                COL_PHONENUMBER + " INTEGER)";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun inssrtData (contact: Contact){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, contact.name)
        cv.put(COL_LASTNAME, contact.lastname)
        cv.put(COL_PHONENUMBER, contact.phonenumber)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

}
