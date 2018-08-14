package com.example.a259.sqltest

class Contact{
    var id : Int = 0
    var name : String = ""
    var lastname : String = ""
    var phonenumber : Int = 0

    constructor(name: String, lastname: String, phonenumber: Int){
        this.name = name
        this.lastname = lastname
        this.phonenumber = phonenumber
    }
}