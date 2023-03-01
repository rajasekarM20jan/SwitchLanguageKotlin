package com.example.switchlanguagekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback

class NewRecipePage : AppCompatActivity() {
    lateinit var backButton:Button
    lateinit var saveButton:Button
    lateinit var recipeName:EditText
    lateinit var ingredientsList:EditText
    lateinit var procedure:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe_page)

        backButton=findViewById(R.id.backButton)
        saveButton=findViewById(R.id.saveButton)
        recipeName=findViewById(R.id.recipeName)
        ingredientsList=findViewById(R.id.ingredientsList)
        procedure=findViewById(R.id.procedure)

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finish()
            }
        })

        backButton.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}