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
        var firstName = nameEditText.text.toString()
        var lastName = lastNameEditText.text.toString()
        var phoneNumber = phoneEditText.text.toString()

        if (firstName.length > 0 &&
                lastName.length > 0 &&
                phoneNumber.length > 0){
            var contact = Contact(firstName, lastName,
                    phoneNumber.toInt())
            var db = DatabaseHandler(this)
            db.inssrtData(contact)
        }else{
            toast("Please fill all fields")
        }
    }
}
