package com.example.switchlanguagekotlin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.firebase.ui.auth.ui.ProgressView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewRecipePage : AppCompatActivity() {
    lateinit var backButton:Button
    lateinit var saveButton:TextView
    private lateinit var recipeName:EditText
    lateinit var ingredientsList:EditText
    lateinit var procedure:EditText
    private lateinit var prepTime:EditText
    lateinit var dietary:RadioGroup
    private lateinit var selectedText:String
    lateinit var p:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe_page)

        backButton=findViewById(R.id.backButton)
        saveButton=findViewById(R.id.saveButton)
        recipeName=findViewById(R.id.recipeName)
        ingredientsList=findViewById(R.id.ingredientsList)
        procedure=findViewById(R.id.procedure)
        prepTime=findViewById(R.id.prepTime)
        dietary=findViewById(R.id.dietaryGroup)
        val db=Firebase.firestore
        selectedText=""

        try{
            val selectedRadioButtonId=dietary.checkedRadioButtonId

            val selectedRadioButton=findViewById<RadioButton>(selectedRadioButtonId)

            selectedText=selectedRadioButton.text.toString()
        }catch (e:Exception){
            e.printStackTrace()
        }


        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finish()
            }
        })

        backButton.setOnClickListener(View.OnClickListener {
            finish()
        })


        saveButton.setOnClickListener(View.OnClickListener {

            if(recipeName.text.length>=4 && ingredientsList.text.length>=20 && procedure.text.length>=50 && prepTime.text.isNotEmpty()){
                if(selectedText != ""||selectedText.isNotEmpty()){
                    p=ProgressDialog(this)
                    p.setMessage("Loading")
                    p.show()
                    var recipeTitle:String=recipeName.text.toString()
                    var recipeIngredients:String=ingredientsList.text.toString()
                    var recipeProcedure:String=procedure.text.toString()
                    var preparationTime:String=prepTime.text.toString()+" minutes"

                    var recipe= hashMapOf(
                        "Ingredients" to recipeIngredients,
                        "Procedure" to recipeProcedure,
                        "Preparation time" to preparationTime,
                        "Dietary" to selectedText
                    )

                    db.collection("RecipeBook").document(recipeTitle).set(recipe)
                        .addOnSuccessListener {
                            if(p.isShowing){
                                p.dismiss()
                            }
                            Toast.makeText(this,"Thank You for sharing your recipe with us.",Toast.LENGTH_SHORT).show()
                            finish()
                        }
                }else{
                    Toast.makeText(this,"Oops! You missed the dietary",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Aww! Seems you missed some thing for the bucket",Toast.LENGTH_SHORT).show()
            }
        })
    }
}