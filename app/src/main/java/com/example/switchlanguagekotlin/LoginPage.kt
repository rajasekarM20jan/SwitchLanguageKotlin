package com.example.switchlanguagekotlin

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.util.*

class LoginPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var googleSignIn:CardView
    lateinit var signIn:GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        auth = FirebaseAuth.getInstance()
        googleSignIn=findViewById(R.id.googleSignIn)

        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        signIn=GoogleSignIn.getClient(this,gso)



        googleSignIn.setOnClickListener(View.OnClickListener {
            try{
                signInGoogle()
            }catch(e:Exception){
                e.printStackTrace()
            }
        })
    }

    private fun signInGoogle(){
        val signInIntent=signIn.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
            if(result.resultCode==Activity.RESULT_OK){
                val task=GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleData(task)
            }else{
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
            }

    }

    private fun handleData(task: Task<GoogleSignInAccount>){
        if(task.isSuccessful){
            val myAccount:GoogleSignInAccount?=task.result
            if(myAccount!=null){
                updateFireBase(myAccount)
            }else{
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun updateFireBase(myAccount: GoogleSignInAccount) {
        val credential=GoogleAuthProvider.getCredential(myAccount.idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener { result->
            if(result.isSuccessful){
                startActivity(Intent(this,Dashboard::class.java))
            }else{
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }
    }
}