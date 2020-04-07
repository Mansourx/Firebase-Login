package com.example.firebase_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mFireBaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFireBaseAuth = FirebaseAuth.getInstance()
        setSignUpBtnListener()
    }


    private fun setSignUpBtnListener() {
        sign_up_btn.setOnClickListener {
            val emailID: String =  email_et.text.toString()
            val password: String = pass_et.text.toString()

            if (emailID.isEmpty() && password.isEmpty()) {
                email_et.error = "Please enter your email ID!"
                pass_et.error = "Please enter your Password!"
                email_et.requestFocus()
                pass_et.requestFocus()
                Toast.makeText(this, "Fields are empty!", Toast.LENGTH_LONG).show()
            } else if (password.isEmpty()) {
                pass_et.error = "Please enter your Password!"
                pass_et.requestFocus()
            } else if (emailID.isEmpty()) {
                email_et.error = "Please enter your email ID!"
                email_et.requestFocus()
            } else if (emailID.isNotEmpty() && password.isNotEmpty()) {
                mFireBaseAuth.createUserWithEmailAndPassword(emailID,password)
                    .addOnCompleteListener(this, OnCompleteListener {
                        if (!it.isSuccessful) {
                            Toast.makeText(this, "Error Registering, Please Try again!",
                                Toast.LENGTH_LONG).show()
                        } else {
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                        }
                    })
            }

            /*Toast.makeText(this, "Sign up clicked!", Toast.LENGTH_LONG).show()*/
        }

        user_sign_in_tv.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}
