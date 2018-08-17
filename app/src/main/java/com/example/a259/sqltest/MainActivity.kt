package com.example.a259.sqltest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    var db = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Update.setOnClickListener{
            db.updateData()
            btn_read.performClick()
        }

        btn_delete.setOnClickListener{
            db.deleteData()
            btn_read.performClick()
        }

   }

    fun readBtnClicked(view: View){
        var data = db.readData()
        tvresult.text = ""
        for (i in 0..(data.size-1)){
            var get = data.get(i)
            tvresult.append("${get.id.toString()}. ${get.name} ${get.lastname},  ${get.phonenumber} \n")
        }
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
            db.inssrtData(contact)
        }else{
            toast("Please fill all fields")
        }
    }
}
