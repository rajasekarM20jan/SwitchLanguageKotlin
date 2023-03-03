package com.example.switchlanguagekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import com.google.android.material.internal.ViewUtils.dpToPx

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