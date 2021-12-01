package com.turbazik.healthera.data.storage

import android.content.Context

private const val USER_STORAGE_TOKEN = "USER_STORAGE_TOKEN"
private const val USER_STORAGE_USER_ID = "USER_STORAGE_USER_ID"
private const val USER_STORAGE_NAME = "USER_STORAGE_NAME"
private const val USER_STORAGE_SURNAME = "USER_STORAGE_SURNAME"
private const val USER_STORAGE_CLIENT_ID = "USER_STORAGE_CLIENT_ID"
private const val USER_STORAGE = "USER_STORAGE"
private const val DEFAULT_CLIENT_ID = "mzUFOsQJLESdYOY"

class UserStorageImpl(context: Context) : UserStorage {

    private val sp = context.getSharedPreferences(USER_STORAGE, Context.MODE_PRIVATE)
    private val editor = sp.edit()

    override var token: String
        get() = sp.getString(USER_STORAGE_TOKEN, null).orEmpty()
        set(value) = editor.putString(USER_STORAGE_TOKEN, value).apply()

    override var userId: String
        get() = sp.getString(USER_STORAGE_USER_ID, null).orEmpty()
        set(value) = editor.putString(USER_STORAGE_USER_ID, value).apply()

    override var name: String
        get() = sp.getString(USER_STORAGE_NAME, null).orEmpty()
        set(value) = editor.putString(USER_STORAGE_NAME, value).apply()

    override var surname: String
        get() = sp.getString(USER_STORAGE_SURNAME, null).orEmpty()
        set(value) = editor.putString(USER_STORAGE_SURNAME, value).apply()

    override var clientId: String
        get() = sp.getString(USER_STORAGE_CLIENT_ID, DEFAULT_CLIENT_ID).orEmpty()
        set(value) = editor.putString(USER_STORAGE_CLIENT_ID, value).apply()

    override fun clear() {
        if (sp.contains(USER_STORAGE_NAME)) {
            editor.remove(USER_STORAGE_NAME).apply()
        }
        if (sp.contains(USER_STORAGE_USER_ID)) {
            editor.remove(USER_STORAGE_USER_ID).apply()
        }
        if (sp.contains(USER_STORAGE_TOKEN)) {
            editor.remove(USER_STORAGE_TOKEN).apply()
        }
        if (sp.contains(USER_STORAGE_CLIENT_ID)) {
            editor.remove(USER_STORAGE_CLIENT_ID).apply()
        }
    }
}
