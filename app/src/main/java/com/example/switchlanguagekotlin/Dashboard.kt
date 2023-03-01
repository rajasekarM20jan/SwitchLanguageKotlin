package com.example.switchlanguagekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView

class Dashboard : AppCompatActivity() {
    lateinit var addNewRecipe:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        addNewRecipe=findViewById(R.id.addNewRecipe)

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finishAffinity()
            }
            })
        addNewRecipe.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,NewRecipePage::class.java))
        })
    }


}