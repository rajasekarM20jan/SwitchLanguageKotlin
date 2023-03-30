package com.example.switchlanguagekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.cardview.widget.CardView
import com.example.switchlanguagekotlin.adapter.RecipeListAdapter
import com.example.switchlanguagekotlin.model.RecipeListModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Dashboard : AppCompatActivity() {
    lateinit var recipeList:ListView
    lateinit var recipeData:ArrayList<RecipeListModel>
    lateinit var rAdapter: RecipeListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        recipeList=findViewById(R.id.recipeList)
        val db=Firebase.firestore
        recipeData= ArrayList();
        db.collection("RecipeBook").get().addOnSuccessListener { result ->
            for (document in result) {
                Log.i(null, "${document.id} => ${document.data}")
                val recipeName:String=document.id
                val ingredient:String=document.data.get("ingredients").toString()
                val dietary:String=document.data.get("dietary").toString()
                val prepTime:String=document.data.get("prepTime").toString()
                val procedure:String=document.data.get("procedure").toString()
                val mealType:String=document.data.get("mealType").toString()
                Log.i(null, "recipeName : $recipeName")
                Log.i(null, "ingredient : $ingredient")
                Log.i(null, "dietary : $dietary")
                Log.i(null, "prepTime : $prepTime")
                Log.i(null, "procedure : $procedure")
                Log.i(null, "mealType : $mealType")

                recipeData.add(RecipeListModel(recipeName,ingredient,prepTime,procedure,dietary,mealType))
                Log.i(null, "recipeData : $recipeData")
            }

            Log.i(null, "onCreate: $recipeData")
            rAdapter=RecipeListAdapter(this,R.layout.custom_recipe_list,recipeData)
            recipeList.adapter=rAdapter
        }


    }


}