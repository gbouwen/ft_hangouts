package com.example.ft_hangouts

class Contact(var id: Long,
              val firstName: String,
              val lastName: String,
              val company: String,
              val phoneNumber: String,
              val email: String) {

    fun validateInput(): String {
        var errorMessage = "ok"

        errorMessage = checkIfEmpty()
        if (errorMessage != "ok")
            return errorMessage
        errorMessage = checkSize()
        if (errorMessage != "ok")
            return errorMessage
        return errorMessage
    }

    private fun checkIfEmpty(): String {
        var msg: String = "ok"

        if (phoneNumber.isEmpty() || firstName.isEmpty()) {
            msg = "Error: Fields with a '*' can't be empty"
        }
        return msg
    }

    private fun checkSize(): String {
        var msg: String = "ok"

        if (firstName.length > 32) {
            msg = "Error: Maximum first name length = 32"
        } else if (lastName.length > 32) {
            msg = "Error: Maximum last name length = 32"
        } else if (company.length > 32) {
            msg = "Error: Maximum company name length = 32"
        } else if (phoneNumber.length > 32) {
            msg = "Error: Maximum phone number length = 32"
        } else if (email.length > 32) {
            msg = "Error: Maximum email length = 32"
        }
        return msg
    }
}