package com.example.a259.sqltest

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.util.jar.Attributes

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

    fun readData () : MutableList<Contact>{
        var list : MutableList<Contact> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                var  contact = Contact()
                contact.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                contact.name = result.getString(result.getColumnIndex(COL_NAME))
                contact.lastname = result.getString(result.getColumnIndex(COL_LASTNAME))
                contact.phonenumber = result.getString(result.getColumnIndex(COL_PHONENUMBER)).toInt()
                list.add(contact)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }


    fun updateData () {
        val db = this.writableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                var cv = ContentValues()
                cv.put(COL_PHONENUMBER, result.getInt(result.getColumnIndex(COL_PHONENUMBER))+100)
                db.update(TABLE_NAME, cv, COL_ID + " =? AND " + COL_NAME + " =? AND " + COL_LASTNAME + " =?",
                        arrayOf(result.getString(result.getColumnIndex(COL_ID)),
                                result.getString(result.getColumnIndex(COL_NAME)),
                                result.getString(result.getColumnIndex(COL_LASTNAME))))
            }while (result.moveToNext())
        }
        result.close()
        db.close()
    }

    fun deleteData (){
        val db = writableDatabase
        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(4.toString()))
        db.close()
    }

}
