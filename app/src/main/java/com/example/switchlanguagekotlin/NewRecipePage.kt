package com.example.switchlanguagekotlin

import android.app.ProgressDialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.switchlanguagekotlin.adapter.IngredientAdapter
import com.example.switchlanguagekotlin.model.IngredientModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.properties.Delegates


class NewRecipePage : AppCompatActivity() {
    lateinit var backButton:Button
    lateinit var saveButton:TextView
    private lateinit var recipeName:EditText
    lateinit var procedure:EditText
    private lateinit var prepTime:EditText
    lateinit var dietary:RadioGroup
    lateinit var ingredientList:ArrayList<IngredientModel>
    lateinit var adapter: IngredientAdapter
    lateinit var gridView: GridView
    lateinit var add:ImageView
    lateinit var ingredientName:EditText
    lateinit var ingredientCount:EditText
    private lateinit var selectedText:String
    lateinit var p:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe_page)

        backButton=findViewById(R.id.backButton)
        saveButton=findViewById(R.id.saveButton)
        recipeName=findViewById(R.id.recipeName)
        procedure=findViewById(R.id.procedure)
        prepTime=findViewById(R.id.prepTime)
        dietary=findViewById(R.id.dietaryGroup)
        ingredientName=findViewById(R.id.ingredientName)
        ingredientCount=findViewById(R.id.ingredientCount)
        gridView=findViewById(R.id.gridViewIng)
        add=findViewById(R.id.ingredientAdd)
        val db=Firebase.firestore
        selectedText=""
        ingredientList= ArrayList()
        var count:Int=0
        try{
            val selectedRadioButtonId=dietary.checkedRadioButtonId

            val selectedRadioButton=findViewById<RadioButton>(selectedRadioButtonId)

            selectedText=selectedRadioButton.text.toString()
            println(selectedText)
        }catch (e:Exception){
            e.printStackTrace()
        }

        add.setOnClickListener(View.OnClickListener {
            if(ingredientName.text.isNotEmpty() && ingredientCount.text.isNotEmpty()){
                var myIngredient:String= ingredientName.text.toString() + " | " +ingredientCount.text.toString()
                ingredientList.add(IngredientModel(myIngredient))
                adapter= IngredientAdapter(this,R.layout.custom_ingredient_view,ingredientList,this)
                gridView.adapter=adapter
                ingredientName.text.clear()
                ingredientCount.text.clear()
                ingredientName.requestFocus()
                if(count>=2){
                    count=1
                    val layoutParams: ViewGroup.LayoutParams = gridView.getLayoutParams()
                    layoutParams.height += convertDpToPixels(140F) //this is in pixels
                    gridView.setLayoutParams(layoutParams)
                }else{
                    count+=1
                }
            }else{
                Toast.makeText(this,"Fill in the previous fields first",Toast.LENGTH_SHORT).show()
            }
        })

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finish()
            }
        })

        backButton.setOnClickListener(View.OnClickListener {
            finish()
        })


        saveButton.setOnClickListener(View.OnClickListener {

            /*{
                p=ProgressDialog(this)
                p.setMessage("Loading")
                p.show()
                var recipeTitle:String=recipeName.text.toString()
                var recipeProcedure:String=procedure.text.toString()
                var preparationTime:String=prepTime.text.toString()+" minutes"

                var recipe= hashMapOf(
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
            }*/
        })
    }

    public fun resetAdapter(){
        adapter= IngredientAdapter(this,R.layout.custom_ingredient_view,ingredientList,this)
        gridView.adapter=adapter
    }

    fun convertDpToPixels(dp: Float): Int {
        val resources: Resources = this.getResources()
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.getDisplayMetrics()
        ).toInt()
    }
}