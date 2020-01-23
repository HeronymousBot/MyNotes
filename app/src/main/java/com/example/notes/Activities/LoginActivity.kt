package com.example.notes.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.notes.R
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textInputLayout : TextInputLayout = findViewById(R.id.textInputLayout_Login)
        val editText : EditText = findViewById(R.id.edittext_userIdLogin)
        val buttonEntrar : Button = findViewById(R.id.button_entrarLogin)

        if(editText.hasFocus()){
            editText.setHintTextColor(resources.getColor(R.color.white, ))
            editText.setHint("Your user ID is a number between 1 and 10.")
        }
    }
}


