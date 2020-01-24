package com.example.notes.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.Utils.SessionManager
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textInputLayout: TextInputLayout = findViewById(R.id.inputlayout_login)
        val editText: EditText = findViewById(R.id.edittext_login)
        val buttonEntrar: Button = findViewById(R.id.button_entrarLogin)
        val progressBar: ProgressBar = findViewById(R.id.progressBar_loginActivity)



        buttonEntrar.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            if (isValidID(editText.text.toString())) {

                SessionManager().saverUserId(editText.text.toString(), applicationContext)
                progressBar.visibility = View.GONE
                startActivity(Intent(this, MainActivity::class.java))
                var toast: Toast = Toast.makeText(
                    this.applicationContext,
                    "Login successful!",
                    Toast.LENGTH_LONG
                )

                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            } else {
                progressBar.visibility = View.GONE
                textInputLayout.error = "User ID is a number between 1 and 10."
            }
        }

        editText.setOnFocusChangeListener { view: View, hasFocus: Boolean ->

            if (hasFocus) {
                textInputLayout.editText!!.setHint("Your User ID is a number between 1 and 10.")
            }


        }


    }

    fun isValidID(id: String): Boolean {
        val validUserIds = resources.getStringArray(R.array.validUserIDs)
        for (i in validUserIds) {
            if (i.toString() == id)
                return true
        }

        return false
    }
}


