package neit.example.SQLListDBExample

class Contact {

    //private variables
    // getting ID
    // setting id
    var id: Int = 0
    // getting name
    // setting name
    var name: String = ""
    // getting phone number
    // setting phone number
    var phoneNumber: String = ""

    // Empty constructor
    constructor() {

    }

    // constructor
    constructor(id: Int, name: String, _phone_number: String) {
        this.id = id
        this.name = name
        this.phoneNumber = _phone_number
    }

    // constructor
    constructor(name: String, _phone_number: String) {
        this.name = name
        this.phoneNumber = _phone_number
    }
}
