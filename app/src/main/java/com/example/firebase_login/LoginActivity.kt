package com.example.firebase_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var mFirebaseAuth: FirebaseAuth
    lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sign_in_btn.setOnClickListener {
            mAuthStateListener = FirebaseAuth.AuthStateListener {
                val mFirebaseUser: FirebaseUser? = mFirebaseAuth.currentUser
                if (mFirebaseUser != null) {
                    Toast.makeText(
                        this, "There is a user loggedIn!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this, "There is No user loggedIn!", Toast.LENGTH_LONG).show()
                }
            }
        }

        user_sign_up_tv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
