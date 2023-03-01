package com.example.switchlanguagekotlin

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class LandingPage : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        firebaseAuth=FirebaseAuth.getInstance()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (firebaseAuth.currentUser == null)
                    startActivity(Intent(this, LoginPage::class.java))
                else startActivity(Intent(this, Dashboard::class.java))
            }, 3000)
    }
}

