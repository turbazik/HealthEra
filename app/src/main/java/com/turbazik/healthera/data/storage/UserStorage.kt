package com.turbazik.healthera.data.storage

interface UserStorage {
    var token: String
    var userId: String
    var name: String
    var surname: String
    var clientId: String

    fun clear()
}