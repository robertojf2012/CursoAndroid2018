package com.example.robert.loginfirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.register_layout.view.*

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            loginUser()
        }
    }

    fun registerPage(){
        val myDialogView = layoutInflater.inflate(R.layout.register_layout,null)
        val mBuilder = AlertDialog.Builder(this).setView(myDialogView)
        val mAlertDialog = mBuilder.show()

        myDialogView.btnRegister.setOnClickListener {
            val email = myDialogView.etEmail.text.toString()
            val password = myDialogView.etPassword.text.toString()
            val password2 = myDialogView.etPassword2.text.toString()

            if(email!="" && password!="" && password2!="" && password.equals(password2) && password.length>=6 && password2.length>=6){
                createUser(email,password)
                mAlertDialog.dismiss()
            }else{
                Toast.makeText(this,"Las contrasenas no coinciden (deben ser de 6 caracteres minimo))",Toast.LENGTH_SHORT).show()
            }
        }
        myDialogView.btnCancelar.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    fun createUser(email: String, password: String){
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in: success
                    // update UI for current User
                    val user = mAuth!!.currentUser
                    Toast.makeText(this,"Usuario creado",Toast.LENGTH_SHORT).show()
                } else {
                    // Sign in: fail
                    Toast.makeText(this,"Error making the user",Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun loginUser(){
        var email = txtEmail.text.toString()
        var password = txtPassword.text.toString()

        if(email!=null && password!=null && email!="" && password!=""){
            mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in: success
                        // update UI for current User
                        val user = mAuth!!.getCurrentUser()
                        tvResult.setText("LOGIN!!!")
                    } else {
                        // Sign in: fail
                        //Log.e(TAG, "signIn: Fail!", task.exception)
                        tvResult.setText("Login fail")
                    }
                }
        }else{
            Toast.makeText(this,"Campos vacios",Toast.LENGTH_SHORT).show()
        }
    }

    //FOR THE MENU
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.btnRegister){
            registerPage()
        }
        return true
    }

}
