package com.example.a259.sqltest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   }

    fun saveButtonClicked(view: View) {
        if (nameEditText.text.toString().length > 0 &&
                lastNameEditText.text.toString().length > 0 &&
                phoneEditText.text.toString().length > 0){
            var contact = Contact(nameEditText.text.toString(), lastNameEditText.text.toString(),
                    phoneEditText.text.toString().toInt())
            var db = DatabaseHandler(this)
            db.inssrtData(contact)
        }else{
            toast("Please fill all fields")
        }
    }
}
