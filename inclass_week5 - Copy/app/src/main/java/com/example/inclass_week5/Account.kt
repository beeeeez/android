package com.example.inclass_week5

class Account{
    //private variables
    // getting ID
    // setting id
    var id: Int = 0
    // getting name
    // setting name
    var name: String = ""
    // getting phone number
    // setting phone number
    var type: String = ""

    // Empty constructor
    constructor() {

    }

    // constructor
    constructor(id: Int, name: String, type: String) {
        this.id = id
        this.name = name
        this.type = type
    }

    // constructor
    constructor(name: String, type: String) {
        this.name = name
        this.type = type
    }
}
